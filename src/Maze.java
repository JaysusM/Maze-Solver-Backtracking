import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Stack;

public class Maze extends JPanel implements ActionListener {

    private Timer timerMove = new Timer(1000, this);
    private Map maze;
    private Position currentPosition;
    private final int pointerConversionRatio = 10;
    private final ArrayList<Tuple> moves = new ArrayList<>();
    private Stack<Node> paths = new Stack<>();

    public Maze()
    {
        maze = new Map();
        currentPosition = maze.getStartPoint().moveNewPosition(new Tuple(0,0));
        moves.add(Tuple.UP);
        moves.add(Tuple.DOWN);
        moves.add(Tuple.LEFT);
        moves.add(Tuple.RIGHT);
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
        g.fillRect(currentPosition.getX()*Map.getRectangleSizeHeight()
                        + pointerConversionRatio/2,
                currentPosition.getY()*Map.getRectangleSizeWidth()
                        + pointerConversionRatio/2,
                Map.getRectangleSizeWidth() - pointerConversionRatio,
                Map.getRectangleSizeHeight() -  pointerConversionRatio);
        timerMove.start();
    }

    public void actionPerformed(ActionEvent e) {
        if (currentPosition != maze.getFinishPoint()) {
            backTrackingSolver();
            repaint();
        }
    }

    public void backTrackingSolver()
    {
        Node current = checkPaths(currentPosition);
        paths.add(current);
        currentPosition.move(current.getPaths().get(0));
    }

    private Node checkPaths(Position pos)
    {
        ArrayList<Tuple> paths = new ArrayList<>();
        for(Tuple t : moves)
        {
            if(Position.checkPosInMaze(currentPosition.moveNewPosition(t), maze) == 0)
                paths.add(t);
        }

        return new Node(pos, paths);
    }

}