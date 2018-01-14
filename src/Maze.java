import javax.swing.*;
import java.awt.*;

public class Maze extends JPanel {

    private Map maze;
    private Position current;
    private final int pointerConversionRatio = 10;

    public Maze()
    {
        maze = new Map();
    }

    /**
     * That method will draw the maze
     * @param g
     */

    public void paintComponent(Graphics g)
    {

        super.paintComponent(g);
        this.setBackground(Color.darkGray);
        maze.drawMap(g);
        g.setColor(new Color(230,167,19));
        g.fillRect(maze.getStartPoint().getX()*Map.getRectangleSizeHeight()
                        + pointerConversionRatio/2,
                maze.getStartPoint().getY()*Map.getRectangleSizeWidth()
                        + pointerConversionRatio/2,
                Map.getRectangleSizeWidth() - pointerConversionRatio,
                Map.getRectangleSizeHeight() -  pointerConversionRatio);
    }
    
}