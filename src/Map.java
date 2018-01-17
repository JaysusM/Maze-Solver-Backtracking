import java.awt.*;

/** Map class
 *  It shows the map drawn
 */

public class Map {

    /**
     * It will describe the maze as a matrix
     * '1' will be a wall, it can't be crossed
     * '0' will be the path, where the pointer
     * can move
     */
    private int[][] map;
    private Position startPoint;
    private Position finishPoint;

    private static final int sizeWidth = 16;
    private static final int sizeHeight = 12;

    private static final int frameWidth = 640;
    private static final int frameHeight = 480;

    public Map (int[][] map, Position startPoint, Position finishPoint)
    {
        this.map = map;
        this.startPoint = startPoint;
        this.finishPoint = finishPoint;
    }

    public Position getStartPoint() {
        return startPoint;
    }

    public Position getFinishPoint() {
        return finishPoint;
    }

    public int[][] getMap() {
        return map;
    }

    public static int getSizeHeight() {
        return sizeHeight;
    }

    public static int getSizeWidth() {
        return sizeWidth;
    }

    public static int getRectangleSizeHeight()
    {
        return frameHeight / sizeHeight;
    }

    public static int getRectangleSizeWidth()
    {
        return frameWidth / sizeWidth;
    }

    public static int getFrameHeight() {
        return frameHeight;
    }

    public static int getFrameWidth() {
        return frameWidth;
    }

    public void drawMap(Graphics g)
    {
        for(int w = 0; w < sizeWidth; w++)
        {
            for(int h = 0; h < sizeHeight; h++)
            {
                if((h == startPoint.getY() && w == startPoint.getX())
                        || (h == finishPoint.getY() && w == finishPoint.getX()))
                    g.setColor(new Color(0x1F9645));
                else if(this.getMap()[h][w] == 0) g.setColor(Color.white);
                else g.setColor(Color.black);

                g.fillRect(w*Map.getRectangleSizeWidth() + w,
                        h*Map.getRectangleSizeHeight() + h,
                        Map.getRectangleSizeHeight(),
                        Map.getRectangleSizeWidth());
            }
        }
    }

}
