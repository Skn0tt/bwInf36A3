import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Arrays;

class Triangle {
  final Point2D a;
  final Point2D b;
  final Point2D c;

  Triangle(Line2D a, Line2D b, Line2D c) {
    this.a = Geometry.intersection(a, b);
    this.b = Geometry.intersection(a, c);
    this.c = Geometry.intersection(b, c);
  }

  String hash() {
    Point2D[] points = {a, b, c};

    Arrays.sort(points, (a, b) ->
      (a.getX() != b.getX()) ?
        Double.compare(a.getX(), b.getX()) :
        Double.compare(a.getY(), b.getY())
    );

    return points[0].toString() +
      points[1].toString() +
      points[2].toString();
  }

  boolean hasArea() {
    return !(a.equals(b) && a.equals(c) && b.equals(c));
  }

  Line2D[] sides() {
    return new Line2D[] {
      new Line2D.Double(a, b),
      new Line2D.Double(a, c),
      new Line2D.Double(b, c),
    };
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
