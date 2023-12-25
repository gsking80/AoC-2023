package king.greg.aoc2023;

import java.util.List;
import org.jgrapht.Graph;
import org.jgrapht.alg.StoerWagnerMinimumCut;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;

public class Day25 {

  private final Graph<String, DefaultEdge> graph;

  Day25(final List<String> lines) {
    graph = new DefaultUndirectedGraph<>(DefaultEdge.class);
    for (final var line : lines) {
      var parts = line.split(": +| +");
      graph.addVertex(parts[0]);
      for (int i = 1; i < parts.length; i++) {
        graph.addVertex(parts[i]);
        graph.addEdge(parts[0], parts[i]);
      }
    }
  }

  public int splitScore() {
    StoerWagnerMinimumCut<String, DefaultEdge> minimumCut = new StoerWagnerMinimumCut<>(graph);
    final var test = minimumCut.minCut();
    return test.size() * (graph.vertexSet().size() - test.size());
  }
}
