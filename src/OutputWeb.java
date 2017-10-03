import java.awt.*;
import java.awt.geom.Point2D;
import java.io.*;
import java.util.Scanner;

class OutputWeb {
  private static final File template = new File("web/template.html");

  /**
   * Shows the given triangles in browser
   * @param triangles
   */
  static void show(Triangle... triangles){
    String json = triangles2JSON(triangles);

    try {
      File htmlFile = getPage(json);
      Desktop.getDesktop().browse(htmlFile.toURI());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Creates viewable HTML page from template
   * @param json
   * @return
   * @throws IOException
   */
  private static File getPage(String json) throws IOException {
    Scanner s = new Scanner(template, "UTF-8");
    String text = s.useDelimiter("\\A").next();
    String result = text.replaceAll("##TRIANGLE##", json);

    File f = new File("./web/output.html");

    PrintWriter writer = new PrintWriter(new FileWriter(f));
    writer.print(result);
    writer.close();

    return f;
  }

  /**
   * Builds a JSON Array of given triangles
   * @param triangles
   * @return
   */
  private static String triangles2JSON(Triangle... triangles) {
    StringBuilder b = new StringBuilder();
    b.append("[");
    for (Triangle t : triangles) {
      b.append(triangle2Arr(t));
      b.append(", ");
    }
    b.append("]");
    return b.toString();
  }

  /**
   * Builds a JSON Array of given triangle
   * @param t
   * @return
   */
  private static String triangle2Arr(Triangle t) {
    return
      "[" +
        point2Arr(t.a) +
        ", " +
        point2Arr(t.b) +
        ", " +
        point2Arr(t.c) +
      "]";
  }

  /**
   * Builds a JSON Array of given Point Tuple
   * @param p
   * @return
   */
  private static String point2Arr(Point2D p) {
    return
      "[" +
        p.getX() +
        ", " +
        p.getY() +
      "]";
  }
}
