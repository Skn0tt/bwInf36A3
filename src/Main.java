import java.awt.geom.Line2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

class Main {
  static private final File[] files = new File[]{
    new File("res/dreiecke1.txt")
  };

  public static void main(String[] args) {
    List<Line2D> lines = new ArrayList<>();

    try {
      lines.addAll(Input.einlesen(files));  // Alle Strecken einlesen und anhängen
    } catch(FileNotFoundException exc) {
      System.out.println("Error: " + exc.toString());
      System.exit(1);
    }

    Graph g = new Graph(lines);

    Triangle[] triangles = g.triangles();

    //TODO: Write GUI to Choose
    OutputWeb.show(triangles);

    new OutputSwing(triangles);
    System.out.println(triangles.length);
    for (Triangle t : triangles) System.out.println(t.toString());
  }
}
