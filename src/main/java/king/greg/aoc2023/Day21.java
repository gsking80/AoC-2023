package king.greg.aoc2023;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day21 {

  private static final List<Point> directions = Arrays.asList(
      new Point(0, -1),
      new Point(1, 0),
      new Point(0, 1),
      new Point(-1, 0)
  );
  private final Set<Point> gardenPlots;
  private final int edgeLength;
  private Point startingLocation;

  Day21(final List<String> lines) {
    gardenPlots = new HashSet<>();
    edgeLength = lines.size();
    for (int y = 0; y < lines.size(); y++) {
      final var line = lines.get(y).toCharArray();
      for (int x = 0; x < line.length; x++) {
        if (line[x] == 'S') {
          startingLocation = new Point(x, y);
          gardenPlots.add(startingLocation);
        }
        if (line[x] == '.') {
          gardenPlots.add(new Point(x, y));
        }
      }
    }
  }

  public int reachedInSteps(final int exactSteps) {

    Set<Point> reachable = new HashSet<>();
    reachable.add(startingLocation);
    for (int i = 0; i < exactSteps; i++) {
      final Set<Point> nextReachable = new HashSet<>();
      for (final var location : reachable) {
        for (final var direction : directions) {
          final var testLocation = new Point(location.x + direction.x, location.y + direction.y);
          if (gardenPlots.contains(testLocation)) {
            nextReachable.add(testLocation);
          }
        }
      }
      reachable = nextReachable;
    }
    return reachable.size();
  }

  public long reachedInStepsInfinite(final long exactSteps) {

    final List<Set<Point>> possibleSteps = new ArrayList<>();
    possibleSteps.add(new HashSet<>()); // even
    possibleSteps.add(new HashSet<>()); // odd

    final long[] reachableCounts = new long[3];
    Set<Point> reachableEdge = new HashSet<>();
    reachableEdge.add(startingLocation);
    long steps = 0;
    for (int n = 0; n < 3; n++) {
      while (steps < ((long) n * edgeLength) + (exactSteps % edgeLength)) {
        steps++;
        final Set<Point> nextReachable = new HashSet<>();
        for (final var location : reachableEdge) {
          nextReachable.addAll(wrappedValidSteps(location));
        }
        nextReachable.removeAll(possibleSteps.get((int) (steps % 2)));
        possibleSteps.get((int) (steps % 2)).addAll(nextReachable);
        reachableEdge = nextReachable;
      }
      reachableCounts[n] = (possibleSteps.get((int) (steps % 2)).size());
    }

    // Hooray for math
    var c = reachableCounts[0];
    var aPlusB = reachableCounts[1] - c;
    var fourAPlusTwoB = reachableCounts[2] - c;
    var twoA = fourAPlusTwoB - (2 * aPlusB);
    var a = twoA / 2;
    var b = aPlusB - a;
    var grids = exactSteps / edgeLength;
    return (a * (grids * grids)) + (b * grids) + c;
  }

  private Set<Point> wrappedValidSteps(final Point location) {
    final Set<Point> wrappedValidSteps = new HashSet<>();
    for (final var direction : directions) {
      int x = location.x + direction.x;
      while (x < 0) {
        x += edgeLength;
      }
      x = x % edgeLength;
      int y = location.y + direction.y;
      while (y < 0) {
        y += edgeLength;
      }
      y = y % edgeLength;
      if (gardenPlots.contains(new Point(x, y))) {
        wrappedValidSteps.add(new Point(location.x + direction.x, location.y + direction.y));
      }
    }
    return wrappedValidSteps;
  }
}
