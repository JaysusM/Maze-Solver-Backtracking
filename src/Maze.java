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
    private ArrayList<Tuple> moves;
    private Stack<Position> positionStack;
    private ArrayList<Position> visited;
    private boolean solved;

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
        this.setBackground(new Color(8, 0, 159));
        maze.drawMap(g);
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

    public void backTrackingSolver()
    {
        Stack<Position> pathsInCurrent = getPaths();
        Position lastPosition = currentPosition;
        pathsInCurrent.removeIf(x -> visited.contains(x));
        if (pathsInCurrent.isEmpty())
          currentPosition = positionStack.pop();
        else {
           currentPosition = pathsInCurrent.pop();
           positionStack.addAll(pathsInCurrent);
           positionStack.push(lastPosition);
            }
        visited.add(currentPosition);
    }

    private Stack<Position> getPaths()
    {
        Stack<Position> paths = new Stack<>();
        for(Tuple t : moves)
        {
            Position positionMoved = currentPosition.move(t);
            if(isPath(positionMoved))
                paths.push(positionMoved);
        }
        paths.sort((x,y) -> (Integer.compare(x.getY(), y.getY()) != 0)
            ? Integer.compare(x.getY(), y.getX())
            : Integer.compare(x.getX(), y.getY()));
        return paths;
    }

    private boolean isPath(Position pos)
    {
        int x = pos.getX();
        int y = pos.getY();
        if(x >= 0 && y >= 0
                && y < maze.getMap().length
                && x < maze.getMap()[0].length
                && maze.getMap()[y][x] == 0)
            return true;

        return false;
    }

    private void paintWin(Graphics g)
    {
        g.setColor(new Color(66, 66, 66));
        g.fill3DRect(Map.getFrameWidth()/7, Map.getFrameHeight()/5,
                (int)(Map.getFrameWidth()*0.7), (int)(Map.getFrameHeight()*0.5),
                 true);
        g.setColor(new Color(0, 146, 150));
        g.fill3DRect(Map.getFrameWidth()/7+pointerConversionRatio*2,
                Map.getFrameHeight()/5+pointerConversionRatio*2,
                (int)(Map.getFrameWidth()*0.7)-pointerConversionRatio*4,
                (int)(Map.getFrameHeight()*0.5)-pointerConversionRatio*4,
                true);
        g.setColor(new Color(0, 0, 0));
        g.setFont(new Font("Calibri", Font.BOLD, 50));
        g.drawString("Maze solved!",
                Map.getFrameWidth()/4-pointerConversionRatio*3,
                Map.getFrameHeight()/2);
    }
}