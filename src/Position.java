/**
 *  Position class, it will set
 *  the current position in the maze
 *  and will help us while using backtracking
 *  simplifying the program
 */

public class Position {

    private int x;
    private int y;

    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void moveX(int increment)
    {
        x += increment;
    }

    public void moveY(int increment)
    {
        y += increment;
    }

    public void move(int incrementX, int incrementY)
    {
        moveX(incrementX);
        moveY(incrementY);
    }

    public void move(Tuple tuple) {
        this.move(tuple.getX1(), tuple.getX2());
    }

    public Position moveNewPosition(Tuple t)
    {
        Position res = new Position(x, y);
        res.move(t);
        return res;
    }

    public static int checkPosInMaze(Position pos, Map maze)
    {
        return (pos.getX() >= 0 && pos.getY() >= 0
            && pos.getX() < Map.getSizeHeight() && pos.getY() < Map.getSizeWidth())
            ? maze.getMap()[pos.getY()][pos.getX()] : 1;
    }

    public int getX() { return this.x; }

    public int getY() { return this.y; }

    public String toString()
    {
        return "(" + this.x + ", " + this.y + ")";
    }

    public boolean equals(Object o)
    {
        return (o instanceof Position) && (((Position) o).x == x) && (((Position) o).y == y);
    }

}
