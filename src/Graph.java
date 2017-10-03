import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Graph {
  private final Map<String, Node> nodes = new HashMap<>();

  /**
   * Creates the graph with the lines given
   * @param lines
   */
  public Graph(List<Line2D> lines) {

    // Create all Nodes
    for (Line2D l : lines) {
      Node n = new Node(l);
      nodes.put(n.hash(), n);
    }

    // Add Intersects
    for (Line2D l : lines) {
      for (Line2D j : lines) {
        if (l.equals(j)) continue;

        if (Geometry.intersection(l, j) != null) {
          nodes
            .get(l.toString())
            .intersects
            .add(
              nodes.get(j.toString())
            );
        }
      }
    }
  }

  /**
   * Finds all Triangles in the Graph by searching for circles with a depth of 3
   * @return all Triangles in the Graph
   */
  Triangle[] triangles() {
    Map<String, Triangle> triangles = new HashMap<>();

    for (Node a : nodes.values()) {
      for (Node b : a.intersects) {
        for (Node c : b.intersects) {
          for (Node d : c.intersects) {
            if (a.hash().equals(b.hash())) continue;
            if (a.hash().equals(c.hash())) continue;
            if (b.hash().equals(c.hash())) continue;

            if (a.hash().equals(d.hash())) {
              Triangle t = new Triangle(
                a.value,
                b.value,
                c.value
              );

              if (t.hasArea()) triangles.put(t.hash(), t);
            }
          }
        }
      }
    }

    return  triangles.values().toArray(new Triangle[0]);
  }

  private class Node {
    final List<Node> intersects = new ArrayList<>();
    final Line2D value;

    Node(Line2D value) {
      this.value = value;
    }

    String hash() {
      return value.toString();
    }

    @Override
    public String toString() {
      return
        "Node{" +
          "value=" + value +
        '}';
    }
  }
}
