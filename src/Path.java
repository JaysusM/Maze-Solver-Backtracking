public class Path {

    private Position from;
    private Position to;

    public Path(Position from, Position to)
    {
        this.from = from;
        this.to = to;
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }
}
