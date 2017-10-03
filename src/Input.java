import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Input {
  static List<Line2D> einlesen(File... files) throws FileNotFoundException {
    List<Line2D> strecken = new ArrayList<>();

    for (File f : files) {
      Scanner s = new Scanner(f);

      if (s.hasNextLine()) s.nextLine(); // Jump first line

      while(s.hasNextLine()) {  // Go through Lines
        String line = s.nextLine(); // Get Line
        if (line.isEmpty()) continue;
        String[] values = line.split(" ");

        Point2D a = new Point2D.Float(
          Float.parseFloat(values[0]),
          Float.parseFloat(values[1])
        );
        Point2D b = new Point2D.Float(
          Float.parseFloat(values[2]),
          Float.parseFloat(values[3])
        );

        strecken.add(new Line2D.Float(a, b));
      }
    }

    return strecken;
  }
}
