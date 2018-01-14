import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Stack;

public class Maze extends JPanel implements ActionListener {

    private Timer timerMove = new Timer(500, this);
    private Map maze;
    private Position currentPosition;
    private final int pointerConversionRatio = 10;
    private final ArrayList<Tuple> moves = new ArrayList<>();
    private Stack<Node> paths = new Stack<>();
    private boolean isBacktracking = false;
    private ArrayList<Position> visited = new ArrayList<>();
    private Position last;

    public Maze()
    {
        maze = new Map();
        currentPosition = maze.getStartPoint().moveNewPosition(new Tuple(0,0));
        visited.add(currentPosition);
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
        Tuple currentPath;
        Node current = checkPaths(currentPosition.moveNewPosition(new Tuple(0,0)));

        if(currentPosition.equals(maze.getStartPoint()))
        {
            currentPosition.move(current.getPaths().get(0));
            visited.add(currentPosition.moveNewPosition(new Tuple(0,0)));
        }
        else if(paths.contains(current)) {

            if(isBacktracking) isBacktracking = false;

            if(paths.peek().getPaths().size() == 0)
                throw new MazeException("Solution couldn't be founded.");

            Node currentNode = paths.pop();
            currentPath = currentNode.getPaths().get(0);
            currentNode.deletePath(currentPath);
            if(currentNode.getPaths().size() != 0)
                paths.push(currentNode);
            currentPosition.move(currentPath);

            visited.add(currentPosition.moveNewPosition(new Tuple(0,0)));
        }
        else if(current.getPaths().size() > 2)
        {
            currentPath = current.getPaths().get(0);
            current.deletePath(currentPath);
            paths.push(current);
            currentPosition.move(currentPath);
            visited.add(currentPosition.moveNewPosition(new Tuple(0,0)));
        }
        else
        {
            if(current.getPaths().size() == 2)
            {
                if(isBacktracking)
                {
                    if(last.equals(currentPosition.moveNewPosition(
                            current.getPaths().get(0))))
                        currentPosition.move(current.getPaths().get(1));
                    else
                        currentPosition.move(current.getPaths().get(0));
                    last = currentPosition.moveNewPosition(new Tuple(0,0));
                }
                else
                {
                    if(visited.contains(currentPosition.moveNewPosition
                            (current.getPaths().get(0))))
                        currentPosition.move(current.getPaths().get(1));
                    else {
                        currentPosition.move(current.getPaths().get(0));
                        visited.add(currentPosition.moveNewPosition(new Tuple(0,0)));
                    }
                }
            }
            else
            {
                isBacktracking = true;
                last = currentPosition.moveNewPosition(new Tuple(0,0));
                currentPosition.move(current.getPaths().get(0));
            }
        }
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