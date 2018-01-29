import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Stack;

public class MazeBFS extends Maze {

    private ArrayList<Position> currentPositions = new ArrayList<>();

    public MazeBFS(Map map) {
        super(map);
        currentPositions.add(currentPosition);
    }

    @Override
    public void paintComponent(Graphics g) {
            super.paintComponent(g);
            this.setBackground(new Color(66, 66, 66));
            maze.drawMap(g, visited, backtracked);
            g.setColor(new Color(230, 167, 19));
            for (Position currentPosition : currentPositions) {
                g.fillRect(currentPosition.getX() * Map.getRectangleSizeHeight()
                                + pointerConversionRatio / 2
                                + currentPosition.getX(),
                        currentPosition.getY() * Map.getRectangleSizeWidth()
                                + pointerConversionRatio / 2
                                + currentPosition.getY(),
                        Map.getRectangleSizeWidth() - pointerConversionRatio,
                        Map.getRectangleSizeHeight() - pointerConversionRatio);
            }
            if (solved)
                super.paintWin(g);
            else
                timerMove.start();
    }

    @Override
    protected void backTrackingSolver() {
        Stack<Position> pathsInCurrent = getPaths();
        pathsInCurrent.removeIf(x -> visited.contains(x));
        positionStack.removeAll(pathsInCurrent);
        currentPositions.addAll(pathsInCurrent);

        currentPositions.removeIf(x -> visited.contains(x));

        for(Position x : pathsInCurrent)
            if(!visited.contains(x))
                visited.add(x);
    }

    @Override
    protected Stack<Position> getPaths()
    {
        Stack<Position> paths = new Stack<>();
        for(Position pos : currentPositions) {
            for (Tuple t : moves) {
                Position positionMoved = pos.move(t);
                if (isPath(positionMoved)) {
                    paths.push(positionMoved);
                }
            }
        }
        return paths;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(Position currentPosition : currentPositions)
        {
            if (currentPosition.equals(maze.getFinishPoint())) {
                solved = true;
                repaint();
                timerMove.stop();
                break;
            }
        }
        backTrackingSolver();
        repaint();
    }
}
