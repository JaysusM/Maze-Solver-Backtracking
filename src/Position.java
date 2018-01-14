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

    public int getX() { return this.x; }

    public int getY() { return this.y; }

    public String toString()
    {
        return "(" + this.x + ", " + this.y + ")";
    }

}
