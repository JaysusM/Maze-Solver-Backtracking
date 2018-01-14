import java.util.ArrayList;
import java.util.Comparator;

public class Node {

    private Position position;

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
            int res = Integer.compare(t1.getX1(), t2.getX1());
            if(res != 0)
                return res;
            else
                return Integer.compare(t2.getX2(), t1.getX2());
        }
    }

    public boolean equals(Object o)
    {
        return (o instanceof Node) && (((Node) o).position == this.position);
    }

}
