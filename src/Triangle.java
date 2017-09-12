import java.awt.geom.Point2D;

public class Triangle {
  Point2D a, b, c;

  public Triangle(Point2D a, Point2D b, Point2D c) {
    this.a = a;
    this.b = b;
    this.c = c;
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
