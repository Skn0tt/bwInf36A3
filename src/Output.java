import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

/**
 * Created by simon.knott on 22.09.2017.
 */
public class Output {
    static final double factor = 5;

    private JPanel drawArea;

    List<Triangle> triangles;

    Output(List<Triangle> triangles) {
        this.triangles = triangles;

        JFrame frame = new JFrame("Output");
        frame.setContentPane(drawArea);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        drawArea = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                for (Triangle t : triangles) {
                    drawLine(g, t.a, t.b);
                    drawLine(g, t.b, t.c);
                    drawLine(g, t.a, t.c);
                }
            }

            void drawLine(Graphics g, Point2D a, Point2D b) {
                g.drawLine((int) (a.getX() * factor),(int) (a.getY() * factor),(int) (b.getX() * factor),(int) (b.getY() * factor));
            }
        };
    }
}
