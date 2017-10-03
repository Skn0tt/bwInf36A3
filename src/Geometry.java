import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

class Geometry {
  private final static int factor = 8;

  static Point2D intersection(Line2D line1, Line2D line2) {
    double x0 = line1.getX1();
    double y0 = line1.getY1();

    double x1 = line1.getX2();
    double y1 = line1.getY2();

    double x2 = line2.getX1();
    double y2 = line2.getY1();

    double x3 = line2.getX2();
    double y3 = line2.getY2();

    double nenner = (y1 - y0) * (x3 - x2) - (x1 - x0) * (y3 - y2);

    if (nenner == 0) return null;

    double a =
      ((x3 - x0) * y2  - (x2 - x0) * y3 - (x3 - x2) * y0) / nenner;

    double b =
      ((x1 - x0) * y2  - (x2 - x0) * y1 + (x2 - x1) * y0) / nenner;

    if (a > 1 || a < 0 || b > 1 || b < 0) return null;

    double x = x0 + a * (x1 - x0);
    double y = y0 + a * (y1 - y0);

    return new Point2D.Double(round(x, factor), round(y, factor));
  }

  private static double round(double value, int places) {
    long factor = (long) Math.pow(10, places);
    value = value * factor;
    long tmp = Math.round(value);
    return (double) tmp / factor;
  }
}
