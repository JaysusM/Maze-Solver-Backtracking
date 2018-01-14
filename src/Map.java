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
    private Position startPoint;
    private Position finishPoint;

    private static final int sizeWidth = 16;
    private static final int sizeHeight = 12;

    private static final int frameWidth = 640;
    private static final int frameHeight = 480;

    private int[][] map = {
            {1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,1,1,1,1,1,1,1,1,1,0,0,0,0,1},
            {1,0,1,0,1,0,1,1,1,0,1,0,1,0,0,1},
            {1,0,1,0,0,0,0,1,0,0,0,0,0,0,0,1},
            {1,0,1,0,0,0,0,1,0,0,0,0,0,1,0,1},
            {1,0,1,0,0,1,0,1,0,0,0,0,0,1,0,1},
            {1,0,1,0,0,1,0,1,0,0,0,0,0,1,0,1},
            {1,0,1,1,1,1,0,1,0,0,0,0,0,1,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1},
            {1,0,1,0,1,1,1,1,1,1,1,1,1,1,0,1},
            {1,0,1,0,1,1,1,1,1,1,1,1,1,1,0,0},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0}
    };

    public Map (int[][] map, Position startPoint, Position finishPoint)
    {
        this.map = map;
        this.startPoint = startPoint;
        this.finishPoint = finishPoint;
    }

    public Map()
    {
        startPoint = new Position(1,0);
        finishPoint = new Position (12,16);
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
        for(int y = 0; y < Map.getSizeWidth(); y++)
        {
            for(int x = 0; x < Map.getSizeHeight(); x++)
            {
                if(this.getMap()[x][y] == 0) g.setColor(Color.white);
                else g.setColor(Color.black);

                g.fillRect(y*Map.getRectangleSizeWidth(),
                        x*Map.getRectangleSizeHeight(),
                        Map.getRectangleSizeHeight(),
                        Map.getRectangleSizeWidth());
            }
        }
    }

}
