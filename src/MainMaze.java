import javax.swing.*;
import java.awt.*;

/**
 * This Main will create the maze
 * that the algorithm will solve
 * by using backtracking
 */

public class MainMaze {
    public static void main(int[][] map, Position initialP, Position finalP, int x, int y) {
        JFrame mainFrame = new JFrame("Maze Backtracking");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Maze maze = new Maze(new Map(map, initialP, finalP));
        mainFrame.setContentPane(maze);
        mainFrame.setLocation(x, y);
        mainFrame.setVisible(true);
        mainFrame.setMinimumSize(new Dimension(Map.getFrameWidth() + Map.getSizeWidth(),
                Map.getFrameHeight() + Map.getSizeHeight()
                        + mainFrame.getInsets().top
                        + mainFrame.getInsets().bottom));
        mainFrame.setMaximumSize(new Dimension(Map.getFrameWidth() + Map.getSizeWidth(),
                Map.getFrameHeight() + Map.getSizeHeight()
                        + mainFrame.getInsets().top
                        + mainFrame.getInsets().bottom));
    }
}