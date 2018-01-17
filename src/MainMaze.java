import javax.swing.*;
import java.awt.*;

public class MainMaze {
    public static void main(int[][] map, Position initialP, Position finalP) {
        JFrame mainFrame = new JFrame("Maze Backtracking");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Maze maze = new Maze(new Map(map, initialP, finalP));
        mainFrame.setContentPane(maze);
        mainFrame.setLocation(200, 200);
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