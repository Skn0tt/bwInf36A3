import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.List;

/**
 * Created by simon.knott on 22.09.2017.
 */
class OutputSwing {
  private JPanel drawArea;

  private final Triangle[] triangles;

  OutputSwing(Triangle... triangles) {
    this.triangles = triangles;

    JFrame frame = new JFrame("OutputSwing");
    frame.setContentPane(drawArea);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }

  private void createUIComponents() {
    drawArea = new JPanel() {
      Color[] c = new Color[]{
        new Color(0, 0, 0),
        new Color(255, 0, 0),
        new Color(0, 255, 0),
        new Color(0, 0, 255),
        new Color(0, 255, 255),
        new Color(255, 0, 255),
        new Color(255, 255, 0),
      };

      @Override
      protected void paintComponent(Graphics g) {
        if (triangles.length == 0) return;

        super.paintComponent(g);

        Dimension toSee = drawArea.getSize();
        Dimension toDraw = getDimensions();

        Dimension factor = new Dimension(
          toSee.width / toDraw.width,
          toSee.height / toDraw.height
        );

        int i = 0;
        for (Triangle t : triangles) drawTriangle(g, t, c[i++ % c.length], factor);
      }

      /**
       * Draws a triangle
       * @param g Graphics to draw on
       * @param t Triangle to draw
       * @param c Color to draw
       * @param f Factor to resize the triangle
       */
      void drawTriangle(Graphics g, Triangle t, Color c, Dimension f) {
        for (Line2D l : t.sides()) drawLine(g, l, c, f);
      }

      /**
       * Draws a line
       * @param g Graphics to draw on
       * @param l Line to draw
       * @param c Color to draw
       * @param f Factor to resize the line
       */
      void drawLine(Graphics g, Line2D l, Color c, Dimension f) {
        g.setColor(c);
        g.drawLine(
          (int) (l.getX1() * f.width),
          (int) (l.getY1() * f.height),
          (int) (l.getX2() * f.width),
          (int) (l.getY2() * f.height)
        );
      }
    };
  }

  private Dimension getDimensions() {
    double xMax = 0;
    double yMax = 0;

    for (Triangle t : triangles) {
      for (Line2D s : t.sides()) {
        if (s.getX1() > xMax) xMax = s.getX1();
        if (s.getX2() > xMax) xMax = s.getX2();

        if (s.getY1() > yMax) yMax = s.getY1();
        if (s.getY2() > yMax) yMax = s.getY2();
      }
    }

    return new Dimension((int) xMax, (int) yMax);
  }
}
