import javax.swing.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class InputReader {
  /**
   * Reads in all Lines from the given files
   * @param files
   * @return
   * @throws FileNotFoundException
   */
  static List<Line2D> readFromFile(File... files) throws FileNotFoundException {
    List<Line2D> strecken = new ArrayList<>();

    for (File f : files) strecken.addAll(read(new Scanner(f)));

    return strecken;
  }

  static List<Line2D> readFromString(String s) {
    return read(new Scanner(s));
  }

  //TODO: check if legal input, throw exception if not
  private static List<Line2D> read(Scanner s) {
    List<Line2D> strecken = new ArrayList<>();

    while(s.hasNextLine()) {  // Go through Lines
      String line = s.nextLine(); // Get Line
      if (line.isEmpty()) continue;
      String[] values = line.split(" ");

      if (values.length != 4) continue; // Malformed Line

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

    return strecken;
  }
}
