public class Tuple {

    private int x1;
    private int x2;

    public Tuple(int x1, int x2)
    {
        this.x1 = x1;
        this.x2 = x2;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public static final Tuple UP = new Tuple(0,-1);
    public static final Tuple DOWN = new Tuple(0,1);
    public static final Tuple LEFT = new Tuple(-1,0);
    public static final Tuple RIGHT = new Tuple(1,0);

    public String toString()
    {
        return "(" + x1 + ", " + x2 + ")";
    }
}