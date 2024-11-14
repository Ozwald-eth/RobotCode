package RobotSimulation;

import java.util.Scanner;

/**
 * Simple program to show arena with multiple robots
* @author shsmchlr
 *
 */
public class RobotInterface {
	
	private Scanner s;								// scanner used for input from user
    private RobotArena myArena;				// arena in which Robots are shown
    /**
    	 * constructor for RobotInterface
    	 * sets up scanner used for input and the arena
    	 * then has main loop allowing user to enter commands
     */
    public RobotInterface() {
    	 s = new Scanner(System.in);			// set up scanner for user input
    	 myArena = new RobotArena(20, 6);	// create arena of size 20*6
    	
        char ch = ' ';
        do {
        	System.out.print("Enter (A)dd Robot, get (I)nformation, (D)isplay, (M)ove, a(N)imate, (C)ustom Arena, (S)ave, (L)oad or e(X)it > ");
        	ch = s.next().charAt(0);
        	s.nextLine();
        	switch (ch) {
    			case 'A' :
    			case 'a' :
        					myArena.addingRobot();	// add a new Robot to arena
        					break;
        		case 'I' :
        		case 'i' :
        					System.out.println(myArena.toString());
            				break;
            				
        		case 'D' :
        		case 'd' :
        			doDisplay();
        			break;
        			
        		case 'M':
        		case 'm':
        			//move all the robots in the arena
        			myArena.moveAllRobots();
        			//display the updated arena
        			doDisplay();
        			//showing the robot position (updated)
        			System.out.println(myArena.toString());
        			break;
        			
        		case 'N':
        		case 'n':
        			//object using RobotAnimator class created
        			RobotAnimator animator = new RobotAnimator(myArena, 10, 200); //10 moves with 200 milisec delay
        			animator.animate(); //starting the animation
        			break;

				case 'S':
				case 's':
					RobotFileManager.saveArena(myArena);
					break;

				case 'L':
				case 'l':
					RobotArena loadedArena = RobotFileManager.loadArena();
					if (loadedArena != null) {
						myArena = loadedArena;
					}
					break;

				case 'C':
        		case 'c':
        			//creating a user arena
        			myArena = UserArena.createArena();
        			System.out.println("Arena has been created.");
        			break;
        			
        		case 'x' : 	
        		ch = 'X';				// when X detected program ends
        					break;
        	}
    		} while (ch != 'X');						// test if end
        
       s.close();									// close scanner
    }
    
    //method to create and display the arena
    void doDisplay() {
    	//find the size of the arena by using get height and width
    	int arenaW = myArena.getWidth();
    	int arenaH = myArena.getHeight();
    	
    	//use cc to create an object of class
    	ConsoleCanvas c = new ConsoleCanvas(arenaW, arenaH, "32001218");
    	
    	//call showRobots to display robots on canvas
    	myArena.showRobots(c);
    	
    	//displaying the canvas
    	System.out.print(c.toString());
        	
    }
    
	public static void main(String[] args) {
		RobotInterface r = new RobotInterface();	// just call the interface
	}

}
