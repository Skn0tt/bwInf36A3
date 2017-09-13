import java.awt.geom.Point2D;
import java.util.Arrays;

public class Triangle {
  Point2D a, b, c;

  Triangle(Point2D a, Point2D b, Point2D c) {
    this.a = a;
    this.b = b;
    this.c = c;
  }

  String hash() {
    Point2D[] points = {a, b, c};
    Arrays.sort(points, (a, b) -> {
      if (a.getX() != b.getX()) return Double.compare(a.getX(), b.getX());
      return Double.compare(a.getY(), b.getY());
    });

    return points[0].toString() +
      points[1].toString() +
      points[2].toString();
  }

  @Override
  public String toString() {
    return "Triangle{" +
      "a=" + a +
      ", b=" + b +
      ", c=" + c +
      '}';
  }
}
