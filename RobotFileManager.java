package RobotSimulation;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class RobotFileManager {

    //save arena to a file
    public static void saveArena(RobotArena arena) {
        //file chooser activated
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(null);
        //check if file type = okay
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try (PrintWriter writer = new PrintWriter(file)) {
                //save arena dimensions
                writer.println(arena.getWidth() + " " + arena.getHeight());

                //save each robot pos and direction
                for (Robot robot : arena.getRobots()) {
                    writer.println(robot.getX() + " " + robot.getY() + " " + robot.getDirection());
                }

                JOptionPane.showMessageDialog(null, "Arena saved, do not worry :) .");
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Error: Could not save file, please try again :( .");
            }
        }
    }

    //load arena from save file
    public static RobotArena loadArena() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(null);

        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try (Scanner scanner = new Scanner(file)) {
                //read arena dimensions
                int width = scanner.nextInt();
                int height = scanner.nextInt();
                RobotArena arena = new RobotArena(width, height);

                //read each robot pos and direction
                while (scanner.hasNext()) {
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    String directionString = scanner.next();
                    Direction direction = Direction.valueOf(directionString);
                    arena.getRobots().add(new Robot(x, y, direction));
                }
                //success / error handling
                JOptionPane.showMessageDialog(null, "Arena loaded from save file. Have fun! ");
                return arena;
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Error: We could not load your file, please try again :( .");
            } catch (IllegalArgumentException | NoSuchElementException e) {
                JOptionPane.showMessageDialog(null, "Error: This file format is invalid, please try a different file :( .");
            }
        }
        return null;
    }
}
