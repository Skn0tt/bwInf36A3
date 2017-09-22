import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Arrays;

public class Triangle {
  Line2D a, b, c;

  Triangle(Line2D a, Line2D b, Line2D c) {
    this.a = a;
    this.b = b;
    this.c = c;
  }

  String hash() {
    Line2D[] lines = {a, b, c};
    //TODO: Sort

    return lines[0].toString() +
      lines[1].toString() +
      lines[2].toString();
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
