import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
  private Map<String, Node> nodes = new HashMap<>();

  public Graph(List<Line2D> lines) {

    // Create all Nodes
    for (Line2D l : lines) {
      Node n = new Node(l.getP1());
      nodes.put(n.hash(), n);
    }

    // Add Intersects
    for (Line2D l : lines) {
      for (Line2D j : lines) {
        if (l == j) continue;
        if (l.intersectsLine(j)) {
          nodes
            .get(l.getP1().toString())
            .intersects
            .add(
              nodes.get(j.getP1().toString())
            );
        }
      }
    }
  }

  List<Triangle> triangles() {
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

              triangles.put(t.hash(), t);
            }
          }
        }
      }
    }

    return new ArrayList<>(triangles.values());
  }

  private class Node {
    List<Node> intersects = new ArrayList<>();
    Point2D value;

    Node(Point2D value) {
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
