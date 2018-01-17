import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JFrame mainFrame = new JFrame("Maze Backtracking");
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        userMaze maze = new userMaze();
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

        JOptionPane.showMessageDialog(mainFrame,
                "Left click: Create path\n" +
                        "Right Click: Create wall\n" +
                        "Press 'S' to select start point\n" +
                        "Press 'F' to select finish point\n" +
                        "Press 'B' to start using backtracking");
    }
}
