import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  static private final File[] files = new File[]{
    new File("res/dreiecke1.txt"),
  };

  public static void main(String[] args) {
    List<Strecke> strecken = new ArrayList<>();


    try {
      strecken.addAll(einlesen(files));  // Alle Punkte einlesen und anhängen
    } catch(FileNotFoundException exc) {
      System.out.println("Error: " + exc.toString());
      System.exit(1);
    }

    for (Strecke s : strecken) System.out.println(s.toString());
  }

  private static List<Strecke> einlesen(File... files) throws FileNotFoundException {
    List<Strecke> strecken = new ArrayList<>();

    for (File f : files) {
      Scanner s = new Scanner(f);

      if (s.hasNextLine()) s.nextLine(); // Jump first line

      while(s.hasNextLine()) {  // Go through Lines
        String line = s.nextLine(); // Get Line
        String[] values = line.split(" ");

        Point2D a = new Point2D.Float(
          Float.parseFloat(values[0]),
          Float.parseFloat(values[1])
        );
        Point2D b = new Point2D.Float(
          Float.parseFloat(values[2]),
          Float.parseFloat(values[3])
        );

        strecken.add(new Strecke(a, b));
      }
    }

    return strecken;
  }

  protected static class Strecke {
    Point2D a, b;

    Strecke(Point2D a, Point2D b) {
      this.a = a;
      this.b = b;
    }

    @Override
    public String toString() {
      return "Strecke{" +
        "a = " + a +
        ", b = " + b +
        '}';
    }
  }
}
