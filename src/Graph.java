import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
  private Map<String, Node> nodes = new HashMap<>();

  public Graph(List<Line2D> lines) {

    // Create all Nodes
    for (Line2D l : lines) {
      Node n = new Node(l);
      nodes.put(n.hash(), n);
    }

    // Add Intersects
    for (Line2D l : lines) {
      for (Line2D j : lines) {
        if (l == j) continue;
        if (l.intersectsLine(j)) {
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

  List<Triangle> triangles() {
    for (String key : nodes.keySet()) {
      System.out.println(
        nodes
          .get(key)
          .toString()
      );
    }

    return new ArrayList<>();
  }

  private class Node {
    List<Node> intersects = new ArrayList<>();
    Line2D value;

    Node(Line2D value) {
      this.value = value;
    }

    String hash() {
      return value.toString();
    }

    @Override
    public String toString() {
      return "Node{" +
        "value=" + value +
        '}';
    }
  }
}
