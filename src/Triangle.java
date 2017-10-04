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
    Point2D[] points = {
      Geometry.intersection(a, b),
      Geometry.intersection(a, c),
      Geometry.intersection(b, c)
    };

    Arrays.sort(points, this::comparePoints);

    this.a = points[0];
    this.b = points[1];
    this.c = points[2];
  }

  /**
   * Returns a unique hash, computed from the points
   * @return
   */
  String hash() {
    return
      a.toString() +
      b.toString() +
      c.toString();
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
  
  public int compareTo(Triangle b) {
    if (!this.a.equals(b.a)) return comparePoints(this.a, b.a);
    if (!this.b.equals(b.b)) return comparePoints(this.b, b.b);
    return comparePoints(this.c, b.c);
  }

  private int comparePoints(Point2D a, Point2D b) {
    if (a.getX() != b.getX()) return Double.compare(a.getX(), b.getX());
    return Double.compare(a.getY(), b.getY());
  }
}
