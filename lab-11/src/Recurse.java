import javax.swing.*;
import java.awt.*;

public class Recurse extends JComponent {

    private int displayHeight = 800;
    private int displayWidth = 800;

    private int AX = displayWidth / 2;
    private int AY = 0;

    private int BX = displayWidth;
    private int BY = displayHeight;

    private int CX = 0;
    private int CY = displayHeight;

    private int[][] points = new int[][]{{AX, AY}, {BX, BY}, {CX, CY}};

    private int degree = 6;


    public void drawLineTriangle(Graphics g, int[][] points, int degree, Color color) {

        g.setColor(color);

        g.drawLine(points[0][0], points[0][1], points[1][0], points[1][1]);
        g.drawLine(points[1][0], points[1][1], points[2][0], points[2][1]);
        g.drawLine(points[2][0], points[2][1], points[0][0], points[0][1]);

        if (degree > 0) {

            if (degree == 6) {
                color = Color.MAGENTA;
            } else if (degree == 5) {
                color = Color.YELLOW;
            } else if (degree == 4) {
                color = Color.GRAY;
            } else if (degree == 3) {
                color = Color.GREEN;
            } else if (degree == 2) {
                color = Color.CYAN;
            } else if (degree == 1) {
                color = Color.RED;
            }

            int[][] newPoints1 = new int[][]{points[0], getMidpoint(points[0], points[1]), getMidpoint(points[0], points[2])};
            drawLineTriangle(g, newPoints1, degree - 1, color);

            int[][] newPoints2 = new int[][]{points[1], getMidpoint(points[0], points[1]), getMidpoint(points[1], points[2])};
            drawLineTriangle(g, newPoints2, degree - 1, color);

            int[][] newPoints3 = new int[][]{points[2], getMidpoint(points[2], points[1]), getMidpoint(points[0], points[2])};
            drawLineTriangle(g, newPoints3, degree - 1, color);
        }
    }

    public void drawFilledTriangle(Graphics g, int[][] points, int degree, Color color) {

        g.setColor(color);

        int[] xPoints = {points[0][0], points[1][0], points[2][0]};
        int[] yPoints = {points[0][1], points[1][1], points[2][1]};

        Polygon p = new Polygon(xPoints, yPoints, 3);

        g.fillPolygon(p);


        if (degree > 0) {

            if (degree == 6) {
                color = Color.MAGENTA;
            } else if (degree == 5) {
                color = Color.YELLOW;
            } else if (degree == 4) {
                color = Color.GRAY;
            } else if (degree == 3) {
                color = Color.GREEN;
            } else if (degree == 2) {
                color = Color.CYAN;
            } else if (degree == 1) {
                color = Color.RED;
            }


            int[][] newPoints1 = new int[][]{points[0], getMidpoint(points[0], points[1]), getMidpoint(points[0], points[2])};
            drawFilledTriangle(g, newPoints1, degree - 1, color);

            int[][] newPoints2 = new int[][]{points[1], getMidpoint(points[0], points[1]), getMidpoint(points[1], points[2])};
            drawFilledTriangle(g, newPoints2, degree - 1, color);

            int[][] newPoints3 = new int[][]{points[2], getMidpoint(points[2], points[1]), getMidpoint(points[0], points[2])};
            drawFilledTriangle(g, newPoints3, degree - 1, color);

        }
    }

    @Override
    protected void paintComponent(Graphics g) {
//        drawLineTriangle(g, points, degree, Color.BLACK);
        drawFilledTriangle(g, points, degree, Color.BLACK);
    }

    public Dimension getPreferredSize() {
        return new Dimension(displayWidth, displayHeight);
    }

    public int[] getMidpoint(int[] point1, int[] point2) {
        int[] point = new int[2];
        int X = ((point1[0] + point2[0]) / 2);
        int Y = ((point1[1] + point2[1]) / 2);
        point[0] = X;
        point[1] = Y;
        return point;
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.add(new Recurse());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}