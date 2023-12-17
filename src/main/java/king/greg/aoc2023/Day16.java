package king.greg.aoc2023;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;

public class Day16 {

  private static final List<Point> directions = Arrays.asList(
      new Point(0, -1),
      new Point(1, 0),
      new Point(0, 1),
      new Point(-1, 0)
  );
  private final char[][] map;
  private final int xMax;
  private final int yMax;

  Day16(final List<String> lines) {
    map = new char[lines.size()][lines.get(0).length()];
    yMax = lines.size() - 1;
    xMax = lines.get(0).length() - 1;
    for (int y = 0; y < lines.size(); y++) {
      map[y] = lines.get(y).toCharArray();
    }
  }

  public int energizedTiles() {
    return energizedTiles(Pair.of(new Point(0, 0), 1));
  }

  private int energizedTiles(Pair<Point, Integer> currentState) {
    final Set<Pair<Point, Integer>> visited = new HashSet<>();
    final Deque<Pair<Point, Integer>> beams = new ArrayDeque<>();
    final Set<Point> energizedTiles = new HashSet<>();

    beams.add(currentState);

    while (!beams.isEmpty()) {
      final var current = beams.removeFirst();
      if (visited.contains(current)) {
        continue;
      }
      visited.add(current);
      energizedTiles.add(current.getLeft());
      beams.addAll(nextLocations(current));
    }

    return energizedTiles.size();
  }

  public int maxEnergizedTiles() {
    int maxEnergizedTiles = 0;
    for (int x = 0; x <= xMax; x++) {
      var fromTop = energizedTiles(Pair.of(new Point(x, 0), 2));
      if (fromTop > maxEnergizedTiles) {
        maxEnergizedTiles = fromTop;
      }
      var fromBottom = energizedTiles(Pair.of(new Point(x, yMax), 0));
      if (fromBottom > maxEnergizedTiles) {
        maxEnergizedTiles = fromBottom;
      }
    }
    for (int y = 0; y <= yMax; y++) {
      var fromLeft = energizedTiles(Pair.of(new Point(0, y), 1));
      if (fromLeft > maxEnergizedTiles) {
        maxEnergizedTiles = fromLeft;
      }
      var fromRight = energizedTiles(Pair.of(new Point(xMax, y), 3));
      if (fromRight > maxEnergizedTiles) {
        maxEnergizedTiles = fromRight;
      }
    }

    return maxEnergizedTiles;
  }

  private Set<Pair<Point, Integer>> nextLocations(final Pair<Point, Integer> current) {
    final Set<Pair<Point, Integer>> nextLocations = new HashSet<>();
    final char currentTile = map[current.getLeft().y][current.getLeft().x];
    final var direction = current.getRight();
    switch (currentTile) {
      case '.' -> {
        final var next = nextTile(current.getLeft(), current.getRight());
        if (null != next) {
          nextLocations.add(next);
        }
      }
      case '/' -> {
        final var next = nextTile(current.getLeft(),
            (direction % 2) == 1 ? direction - 1 : direction + 1);
        if (null != next) {
          nextLocations.add(next);
        }
      }
      case '\\' -> {
        final var next = nextTile(current.getLeft(),
            (direction % 2) == 1 ? direction + 1 : direction - 1);
        if (null != next) {
          nextLocations.add(next);
        }
      }
      case '|' -> {
        if (direction % 2 == 0) {
          final var next = nextTile(current.getLeft(), direction);
          if (null != next) {
            nextLocations.add(next);
          }
        } else {
          final var nextLeft = nextTile(current.getLeft(), direction - 1);
          if (null != nextLeft) {
            nextLocations.add(nextLeft);
          }
          final var nextRight = nextTile(current.getLeft(), direction + 1);
          if (null != nextRight) {
            nextLocations.add(nextRight);
          }
        }
      }
      case '-' -> {
        if (direction % 2 == 1) {
          final var next = nextTile(current.getLeft(), direction);
          if (null != next) {
            nextLocations.add(next);
          }
        } else {
          final var nextLeft = nextTile(current.getLeft(), direction - 1);
          if (null != nextLeft) {
            nextLocations.add(nextLeft);
          }
          final var nextRight = nextTile(current.getLeft(), direction + 1);
          if (null != nextRight) {
            nextLocations.add(nextRight);
          }
        }
      }
      default -> {
        throw new UnsupportedOperationException(String.valueOf(currentTile));
      }
    }

    return nextLocations;
  }

  private Pair<Point, Integer> nextTile(final Point current, final int rawDirection) {
    final var direction = (rawDirection + 4) % 4;
    final var offset = directions.get(direction);
    final int x = current.x + offset.x;
    final int y = current.y + offset.y;
    if (validLocation(x, y)) {
      return Pair.of(new Point(x, y), direction);
    }
    return null;
  }

  private boolean validLocation(final int x, final int y) {
    return 0 <= x && x <= xMax && 0 <= y && y <= yMax;
  }
}
