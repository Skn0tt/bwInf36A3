import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Arrays;

/**
 * Model for the Triangle
 */
class Triangle {
  final Point2D a;
  final Point2D b;
  final Point2D c;

  Triangle(Line2D a, Line2D b, Line2D c) {
    this.a = Geometry.intersection(a, b);
    this.b = Geometry.intersection(a, c);
    this.c = Geometry.intersection(b, c);
  }

  /**
   * Returns a unique hash, computed from the points
   * @return
   */
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

  /**
   * Checks if Triangles Area is bigger than 0
   * @return
   */
  boolean hasArea() {
    return !(a.equals(b) && a.equals(c) && b.equals(c));
  }

  /**
   * Returns its sides
   * @return
   */
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
  
  public int compareTo(Trianlge b) {
    if (!this.a.equals(b.a)) return comparePoint(this.a, b.a);
    if (!this.b.equals(b.b)) return comparePoint(this.b, b.b);
    return comparePoint(this.c, c.c);
  }

  private int comparePoint(Point2D a, Point2D b) {
    if (a.getX() != b.getX()) return Double.compare(a.getX(), b.getX());
    return Double.compare(a.getY(), b.getY());
  }
}
