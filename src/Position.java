/**
 *  Position class, it will set
 *  the current position in the maze
 *  and will help us while using backtracking
 *  simplifying the program
 */

public class Position {

    private final int x;
    private final int y;

    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Position moveX(int increment)
    {
        return new Position(x + increment, y + increment);
    }

    public Position moveY(int increment)
    {
        return new Position(this.x, y + increment);
    }

    public Position move(int incrementX, int incrementY)
    {
       return new Position(x + incrementX, y + incrementY);
    }

    public Position move(Tuple tuple) {
        return this.move(tuple.getX1(), tuple.getX2());
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
