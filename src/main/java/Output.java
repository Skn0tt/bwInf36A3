import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
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

        for (Triangle t : triangles) drawTriangle(g, t);
      }

      void drawTriangle(Graphics g, Triangle t) {
        for (Line2D l : t.sides()) drawLine(g, l);
      }

      void drawLine(Graphics g, Line2D l) {
        g.drawLine(
          (int) l.getX1(),
          (int) l.getY1(),
          (int) l.getX2(),
          (int) l.getY2()
        );
      }
    };
  }
}
