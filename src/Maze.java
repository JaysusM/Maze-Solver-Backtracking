import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Stack;

public class Maze extends JPanel implements ActionListener {

    protected Timer timerMove = new Timer(500, this);
    protected Map maze;

    protected Position currentPosition;
    protected final int pointerConversionRatio = Map.getRectangleSizeHeight()/4;
    protected ArrayList<Tuple> moves;
    protected Stack<Position> positionStack;
    protected ArrayList<Position> visited;
    protected ArrayList<Position> backtracked;
    protected boolean solved;

    private static final Tuple UP = new Tuple(0,-1);
    private static final Tuple DOWN = new Tuple(0,1);
    private static final Tuple LEFT = new Tuple(-1,0);
    private static final Tuple RIGHT = new Tuple(1,0);

    public Maze(Map maze)
    {
        visited = new ArrayList<>();
        positionStack = new Stack<>();
        moves = new ArrayList<>();
        solved = false;
        backtracked = new ArrayList<>();
        this.maze = maze;

        currentPosition = maze.getStartPoint();
        visited.add(currentPosition);

        positionStack.push(currentPosition);

        moves.add(UP);
        moves.add(DOWN);
        moves.add(LEFT);
        moves.add(RIGHT);
    }

    /**
     * That method will draw the maze
     * @param g
     */

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        this.setBackground(new Color(66, 66, 66));
        maze.drawMap(g, visited, backtracked);
        g.setColor(new Color(230,167,19));
        g.fillRect(currentPosition.getX()*Map.getRectangleSizeHeight()
                        + pointerConversionRatio/2
                        + currentPosition.getX(),
                currentPosition.getY()*Map.getRectangleSizeWidth()
                        + pointerConversionRatio/2
                        + currentPosition.getY(),
                Map.getRectangleSizeWidth() - pointerConversionRatio,
                Map.getRectangleSizeHeight() -  pointerConversionRatio);
        if(solved)
            paintWin(g);
        else
            timerMove.start();
    }

    public void actionPerformed(ActionEvent e) {
        if (!currentPosition.equals(maze.getFinishPoint())) {
            backTrackingSolver();
            repaint();
        } else {
            solved = true;
            repaint();
            timerMove.stop();
        }
    }

    protected void backTrackingSolver()
    {
        Stack<Position> pathsInCurrent = getPaths();
        Position lastPosition = currentPosition;
        pathsInCurrent.removeIf(x -> visited.contains(x));
        positionStack.removeAll(pathsInCurrent);
        if (pathsInCurrent.isEmpty()) {
            backtracked.add(currentPosition);
            currentPosition = positionStack.pop();
        } else {
            currentPosition = pathsInCurrent.pop();
            positionStack.addAll(pathsInCurrent);
            positionStack.push(lastPosition);
        }

        if(!visited.contains(currentPosition))
            visited.add(currentPosition);
    }

    protected Stack<Position> getPaths()
    {
        Stack<Position> paths = new Stack<>();
        for(Tuple t : moves)
        {
            Position positionMoved = currentPosition.move(t);
            if(isPath(positionMoved))
                paths.push(positionMoved);
        }
        paths.sort((x,y) -> (Integer.compare(x.getY(), y.getY()) != 0)
            ? Integer.compare(x.getY(), y.getY())
            : Integer.compare(x.getX(), y.getX()));
        return paths;
    }

    protected boolean isPath(Position pos)
    {
        int x = pos.getX();
        int y = pos.getY();
        if(x >= 0 && y >= 0
                && y < maze.getMap().length
                && x < maze.getMap()[0].length
                && (maze.getMap()[y][x] == 0
                || new Position(x,y)
                .equals(maze.getFinishPoint())))
            return true;

        return false;
    }

    protected void paintWin(Graphics g)
    {
        g.setColor(new Color(255, 255, 255));
        g.setFont(new Font("Calibri", Font.BOLD, 24));
        g.drawString("Maze solved!",
                12,Map.getFrameHeight()+2);
    }
}