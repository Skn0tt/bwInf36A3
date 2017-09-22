import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.LineSegment;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Arrays;

public class Triangle {
  private Point2D a, b, c;

  Triangle(Line2D a, Line2D b, Line2D c) {
    this.a = intersectionPoint(a, b);
    this.b = intersectionPoint(a, c);
    this.c = intersectionPoint(b, c);
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

  private static Point2D intersectionPoint(Line2D a, Line2D b) {
    LineSegment aS = lineSegment(a);
    LineSegment bS = lineSegment(b);

    Coordinate coord = aS.intersection(bS);

    return point2D(coord);
  }

  private static LineSegment lineSegment(Line2D line) {
    return new LineSegment(
      new Coordinate(line.getX1(), line.getY1()),
      new Coordinate(line.getX2(), line.getY2())
    );
  }

  private static Point2D point2D(Coordinate c) {
    return new Point2D.Double(c.x, c.y);
  }
}
