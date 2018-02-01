import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class userMaze extends JPanel {

    private int x;
    private int y;
    private int currentRow;
    private int currentCol;
    private boolean startP, finishP;
    private Position startPoint;
    private Position finishPoint;

    public Position getStartPoint() {
        return startPoint;
    }

    public Position getFinishPoint() {
        return finishPoint;
    }

    public int[][] getMap() {
        return map;
    }

    //Map to be taken as reference
    private int[][] map = {
            {1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,1,1,1,1,1,1,1,1,1,0,0,0,0,1},
            {1,0,1,0,1,1,1,1,1,1,1,0,1,1,0,1},
            {1,0,1,0,0,0,0,0,0,0,0,0,0,1,0,1},
            {1,0,1,0,1,1,0,1,0,1,1,1,0,1,0,1},
            {1,0,1,0,1,1,0,1,0,1,1,1,0,1,0,1},
            {1,0,1,0,1,1,0,1,0,1,1,1,0,1,0,1},
            {1,0,1,1,1,1,0,1,0,1,1,1,1,1,0,1},
            {1,0,0,0,0,0,0,1,0,1,1,1,1,1,0,1},
            {1,0,1,1,0,1,1,1,1,1,1,1,1,1,0,1},
            {1,0,0,0,0,1,1,1,1,1,1,1,1,1,0,0},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0}
    };

    public userMaze() {
        startPoint = new Position(1,0);
        finishPoint = new Position (15,11);
        currentCol = -1;
        currentRow = -1;
        startP = false;
        finishP = false;

        //setMaze();

        this.setFocusable(true);
        this.grabFocus();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    if (startP)
                        startPoint = new Position(
                                e.getX() / Map.getRectangleSizeHeight(),
                                e.getY() / Map.getRectangleSizeWidth()
                        );
                    else if (finishP)
                        finishPoint = new Position(
                                e.getX() / Map.getRectangleSizeHeight(),
                                e.getY() / Map.getRectangleSizeWidth()
                        );
                    else map[getRow()][getCol()] = 0;
                else if (e.getButton() == MouseEvent.BUTTON3)
                    map[getRow()][getCol()] = 1;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                x = -1;
                y = -1;
                currentCol = -1;
                currentRow = -1;
                repaint();
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {
                x = e.getX();
                y = e.getY();
                currentRow = getRow();
                currentCol = getCol();
                repaint();
            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (Character.toLowerCase(e.getKeyChar()) == 's') {
                    startP = !startP;
                    finishP = false;
                } else if (e.getKeyCode() == KeyEvent.VK_F) {
                    finishP = !finishP;
                    startP = false;
                } else if (Character.toLowerCase(e.getKeyChar()) == 'b')
                    closeJFrame();
            }
        });
    }

    private void closeJFrame()
    {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        int x = frame.getX();
        int y = frame.getY();
        createChooseMethod(x,y, frame);
    }

    private void createChooseMethod(int x, int y, JFrame ancestor)
    {
        // Auxiliar panel to choose
        // how to solve maze

        JPanel chooseMethod = new JPanel();

        chooseMethod.setLayout(new FlowLayout());
        JButton dfsButton = new JButton("DFS Method");
        dfsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMaze.main(map, startPoint, finishPoint, x, y, false);
                SwingUtilities.getWindowAncestor(dfsButton).dispose();
                ancestor.dispose();
            }
        });

        JButton bfsButton = new JButton("BFS Button");
        bfsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMaze.main(map, startPoint, finishPoint, x, y, true);
                SwingUtilities.getWindowAncestor(bfsButton).dispose();
                ancestor.dispose();
            }
        });
        chooseMethod.add(dfsButton);
        chooseMethod.add(bfsButton);

        JDialog choose = new JDialog();
        choose.setTitle("Maze method solver");
        choose.add(chooseMethod);
        choose.setVisible(true);
        choose.setPreferredSize(new Dimension(265,72));
        choose.setMinimumSize(new Dimension(265,72));
        choose.setLocationRelativeTo(null);
        choose.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private int getRow() {
        return y / Map.getRectangleSizeHeight();
    }

    private int getCol() {
        return x / Map.getRectangleSizeWidth();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.darkGray);
        this.drawMap(g);
    }

    public void drawMap(Graphics g) {
        for (int w = 0; w < Map.getSizeWidth(); w++) {
            for (int h = 0; h < Map.getSizeHeight(); h++) {
                if (w == currentCol && h == currentRow) g.setColor(Color.gray);
                else if ((h == startPoint.getY() && w == startPoint.getX())
                        || (h == finishPoint.getY() && w == finishPoint.getX()))
                    g.setColor(new Color(0x1F9645));
                else if (map[h][w] == 0) g.setColor(Color.white);
                else g.setColor(Color.black);

                g.fillRect(w * Map.getRectangleSizeWidth() + w,
                        h * Map.getRectangleSizeHeight() + h,
                        Map.getRectangleSizeHeight(),
                        Map.getRectangleSizeWidth());
            }
        }
    }
}
