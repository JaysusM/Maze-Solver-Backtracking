import java.util.ArrayList;
import java.util.Comparator;

public class Node {

    private Position position;
    private boolean visited;

    private ArrayList<Tuple> paths;

    public Node(Position pos, ArrayList<Tuple> paths)
    {
        this.position = pos;
        this.paths = paths;
        this.paths.sort(new leftRightPath());
    }

    public void deletePath(Tuple tuple)
    {
        paths.remove(tuple);
        this.paths.sort(new leftRightPath());
        checkVisited();
    }

    public boolean isVisited()
    {
        return visited;
    }

    public void checkVisited()
    {
        if(paths.isEmpty()) visited = true;
        else visited = false;
    }

    public Position getPosition() {
        return position;
    }

    public ArrayList<Tuple> getPaths() {
        return paths;
    }

    public class leftRightPath implements Comparator<Tuple>
    {
        public int compare(Tuple t1, Tuple t2)
        {
            return (t1.getX1() >= t2.getX2()) ? 1 : -1;
        }
    }

}
