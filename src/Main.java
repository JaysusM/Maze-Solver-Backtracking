import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JFrame mainFrame = new JFrame("Maze Backtracking");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Maze maze = new Maze();
        mainFrame.add(maze);
        mainFrame.setLocation(200,200);
        mainFrame.setVisible(true);
        mainFrame.setMinimumSize(new Dimension(Map.getFrameWidth(),
                Map.getFrameHeight()
                        + mainFrame.getInsets().top
                        + mainFrame.getInsets().bottom));

    }
}
