package king.greg.aoc2023;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day10 {

  private final Map<Point, Character> map;
  private final Set<Point> loop = new HashSet<>();
  private Point start;

  Day10(final List<String> lines, final char startReplacement) {
    map = new HashMap<>();
    for (var y = 0; y < lines.size(); y++) {
      final var currentLine = lines.get(y).toCharArray();
      for (var x = 0; x < currentLine.length; x++) {
        if (currentLine[x] == 'S') {
          start = new Point(x, y);
          currentLine[x] = startReplacement;
        }
        map.put(new Point(x, y), currentLine[x]);
      }
    }
  }

  public int farthestSteps() {
    loop.add(start);
    Set<Point> nextPipes = getAdjacent(start);
    int steps = 0;
    while (!nextPipes.isEmpty()) {
      steps++;
      Set<Point> tempPipes = new HashSet<>();
      for (final var pipe : nextPipes) {
        loop.add(pipe);
        tempPipes.addAll(getAdjacent(pipe));
      }
      tempPipes.removeAll(loop);
      nextPipes = tempPipes;
    }
    return steps;
  }

  public int enclosedArea() {
    farthestSteps();
    final Map<Integer, List<Point>> pipeLines = new HashMap<>();
    for (final var pipe : loop) {
      var pipeline = pipeLines.computeIfAbsent(pipe.y, k -> new ArrayList<>());
      pipeline.add(pipe);
    }
    int enclosedSpaces = 0;
    for (final var pipeline : pipeLines.values()) {
      enclosedSpaces += enclosedSpaces(pipeline);
    }
    return enclosedSpaces;
  }

  private int enclosedSpaces(final List<Point> pipeline) {
    pipeline.sort(Comparator.comparingDouble(Point::getX));
    boolean inside = false;
    int enclosedSpaces = 0;
    double lastX = pipeline.get(0).getX();
    char lastBend = ' ';
    for (final var pipe : pipeline) {
      final var shape = map.get(pipe);
      switch (shape) {
        case '|' -> {
          if (inside) {
            enclosedSpaces += pipe.getX() - lastX;
          }
          inside = !inside;
          lastX = pipe.getX() + 1;
        }
        case '-' -> lastX = pipe.getX() + 1;
        case 'F', 'L' -> {
          if (inside) {
            enclosedSpaces += pipe.getX() - lastX;
          }
          inside = !inside;
          lastX = pipe.getX() + 1;
          lastBend = shape;
        }
        case '7' -> {
          lastX = pipe.getX() + 1;
          if (lastBend == 'F') {
            inside = !inside;
          }
        }
        case 'J' -> {
          lastX = pipe.getX() + 1;
          if (lastBend == 'L') {
            inside = !inside;
          }
        }
        default -> throw new UnsupportedOperationException("Unexpected shape at: " + pipe);
      }
    }
    return enclosedSpaces;
  }

  private Set<Point> getAdjacent(final Point location) {
    final Set<Point> adjacentPipes = new HashSet<>();
    final char pipeShape = map.get(location);
    switch (pipeShape) {
      case '|' -> {
        adjacentPipes.add(new Point(location.x, location.y - 1));
        adjacentPipes.add(new Point(location.x, location.y + 1));
      }
      case '-' -> {
        adjacentPipes.add(new Point(location.x - 1, location.y));
        adjacentPipes.add(new Point(location.x + 1, location.y));
      }
      case 'L' -> {
        adjacentPipes.add(new Point(location.x, location.y - 1));
        adjacentPipes.add(new Point(location.x + 1, location.y));
      }
      case 'J' -> {
        adjacentPipes.add(new Point(location.x, location.y - 1));
        adjacentPipes.add(new Point(location.x - 1, location.y));
      }
      case '7' -> {
        adjacentPipes.add(new Point(location.x - 1, location.y));
        adjacentPipes.add(new Point(location.x, location.y + 1));
      }
      case 'F' -> {
        adjacentPipes.add(new Point(location.x + 1, location.y));
        adjacentPipes.add(new Point(location.x, location.y + 1));
      }
      default -> throw new UnsupportedOperationException("Unexpected shape at: " + location);
    }
    return adjacentPipes;
  }
}
