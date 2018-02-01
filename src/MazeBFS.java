import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

public class MazeBFS extends Maze {

    private ArrayList<Position> currentPositions = new ArrayList<>();
    private ArrayList<Path> movements = new ArrayList<>();

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
                if (isPath(positionMoved) && !visited.contains(positionMoved)) {
                    paths.push(positionMoved);
                    movements.add(new Path(pos, positionMoved));
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
                addSolutionPath();
                repaint();
                timerMove.stop();
                return ;
            }
        }
        backTrackingSolver();
        repaint();
    }


    //TODO Reduce complexity
    private void addSolutionPath()
    {
        ArrayList<Position> way = new ArrayList<>();
        Position cPos = super.maze.getFinishPoint();
        way.add(cPos);
        boolean found = false;
        int counter = 0;

        while(!cPos.equals(super.maze.getStartPoint()))
        {
            while(!found)
            {
                if(movements.get(counter).getTo().equals(cPos))
                {
                    cPos = movements.get(counter).getFrom();
                    found = true;
                    way.add(cPos);
                }
                counter++;
            }
            found = false;
            counter = 0;
        }

        for(int i = 0; i < maze.getMap().length; i++)
        {
            for(int j = 0; j < maze.getMap()[0].length; j++)
            {
                int val = maze.getMap()[i][j];
                Position pos = new Position(j,i);

                if(val == 0 && !way.contains(pos))
                    backtracked.add(pos);
            }
        }

        currentPositions.removeAll(currentPositions);
        currentPositions.add(maze.getFinishPoint());
    }
}
