package king.greg.aoc2023;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

public class Day23 {

  private static final Point[] directions = {
      new Point(0, -1),
      new Point(1, 0),
      new Point(0, 1),
      new Point(-1, 0)
  };
  private final char[][] map;
  private final int xMax;
  private final int yMax;
  private final Map<Pair<Point, Set<Point>>, Integer> memo;
  private final Map<Pair<Point, Point>, Pair<Point, Integer>> edges;

  Day23(final List<String> lines) {
    map = new char[lines.size()][lines.get(0).length()];
    yMax = map.length - 1;
    xMax = map[0].length - 1;
    for (int y = 0; y < lines.size(); y++) {
      map[y] = lines.get(y).toCharArray();
    }
    memo = new HashMap<>();
    edges = new HashMap<>();
  }

  public int longestHike() {
    int longestHike = 0;
    final Queue<State> queue = new ArrayDeque<>();
    queue.add(new State(new Point(1, 0), 0, Set.of(new Point(1, 1)), Collections.emptyList()));
    while (!queue.isEmpty()) {
      final var currentState = queue.remove();
      for (final var nextLocation : currentState.possibleNextLocations) {
        if (nextLocation.y == yMax) {
          var distanceTraveled = currentState.distanceTraveled + 1;
          if (distanceTraveled > longestHike) {
            longestHike = distanceTraveled;
          }
        } else {
          final var nextChoice = nextChoice(currentState.currentLocation, nextLocation);
          var nextChoiceLocation = nextChoice.getMiddle();
          if (!currentState.path.contains(nextChoiceLocation)) {
            final List<Point> newPath = new ArrayList<>(currentState.path);
            newPath.add(nextChoiceLocation);
            queue.add(
                new State(nextChoiceLocation, currentState.distanceTraveled + nextChoice.getLeft(),
                    nextChoice.getRight(), newPath));
          }
        }
      }
    }
    return longestHike;
  }

  public int longestEasyHike() {
    int longest = 0;
    final Deque<Triple<Point, Integer, Set<Point>>> stack = new ArrayDeque<Triple<Point, Integer, Set<Point>>>() {
    };

    stack.push(Triple.of(new Point(1, 0), 0, Collections.emptySet()));
    while (!stack.isEmpty()) {
      var currentState = stack.pop();
      var currentLocation = currentState.getLeft();
      for (int direction = 0; direction < 4; direction++) {
        final Point nextStep = new Point(currentLocation.x + directions[direction].x,
            currentLocation.y + directions[direction].y);
        if (validLocation(nextStep)) {
          final var nextNode = getOtherEnd(currentLocation, nextStep);
          final var nextLocation = nextNode.getLeft();
          if (currentState.getRight().contains(nextLocation)) {
            continue;
          }
          int totalDistance = nextNode.getRight() + currentState.getMiddle();
          if (nextLocation.y == yMax) {
            if (totalDistance > longest) {
              longest = totalDistance;
            }
          } else {
            final Set<Point> visited = new HashSet<>(currentState.getRight());
            visited.add(currentLocation);
            stack.push(Triple.of(nextLocation, totalDistance, visited));
          }
        }
      }
    }
    return longest;
  }

  private Pair<Point, Integer> getOtherEnd(final Point currentLocation, final Point stepOne) {
    var otherEnd = edges.get(Pair.of(currentLocation, stepOne));
    if (otherEnd != null) {
      return otherEnd;
    }
    int stepCount = 0;
    final Queue<Point> queue = new ArrayDeque<>();
    queue.add(stepOne);
    Point previousLocation = currentLocation;
    Point location = currentLocation;
    while (queue.size() == 1) {
      previousLocation = location;
      location = queue.remove();
      stepCount++;
      for (int direction = 0; direction <= 3; direction++) {
        Point nextLocation = new Point(location.x + directions[direction].x,
            location.y + directions[direction].y);
        if (!nextLocation.equals(previousLocation) && validLocation(nextLocation)) {
          if (nextLocation.y == yMax) {
            otherEnd = Pair.of(nextLocation, stepCount + 1);
            edges.put(Pair.of(currentLocation, stepOne), otherEnd);
            return otherEnd;
          }
          queue.add(nextLocation);
        }
      }
    }
    otherEnd = Pair.of(location, stepCount);
    edges.put(Pair.of(currentLocation, stepOne), otherEnd);
    edges.put(Pair.of(location, previousLocation), Pair.of(currentLocation, stepCount));
    return otherEnd;
  }

  final Triple<Integer, Point, Set<Point>> nextChoice(final Point currentLocation,
      final Point nextStep) {
    int stepCount = 0;
    final Queue<Point> queue = new ArrayDeque<>();
    queue.add(nextStep);
    Point previousLocation;
    Point location = currentLocation;
    while (queue.size() == 1) {
      previousLocation = location;
      location = queue.remove();
      stepCount++;
      int directionMin;
      int directionMax;
      var currentSpace = map[location.y][location.x];
      switch (currentSpace) {
        case '^' -> {
          directionMin = 0;
          directionMax = 0;
        }
        case '>' -> {
          directionMin = 1;
          directionMax = 1;
        }
        case 'v' -> {
          directionMin = 2;
          directionMax = 2;
        }
        case '<' -> {
          directionMin = 3;
          directionMax = 3;
        }
        default -> {
          directionMin = 0;
          directionMax = 3;
        }
      }
      for (int direction = directionMin; direction <= directionMax; direction++) {
        Point nextLocation = new Point(location.x + directions[direction].x,
            location.y + directions[direction].y);
        if (!nextLocation.equals(previousLocation) && validLocation(nextLocation)) {
          if (nextLocation.y == yMax) {
            return Triple.of(stepCount, location, Set.of(nextLocation));
          }
          var nextSpace = map[nextLocation.y][nextLocation.x];
          switch (nextSpace) {
            case '^' -> {
              if (direction != 2) {
                queue.add(nextLocation);
              }
            }
            case '>' -> {
              if (direction != 3) {
                queue.add(nextLocation);
              }
            }
            case 'v' -> {
              if (direction != 0) {
                queue.add(nextLocation);
              }
            }
            case '<' -> {
              if (direction != 1) {
                queue.add(nextLocation);
              }
            }
            default -> queue.add(nextLocation);
          }
        }
      }
    }
    final Set<Point> choices = new HashSet<>(queue);
    return Triple.of(stepCount, location, choices);
  }

  private boolean validLocation(final Point location) {
    return location.x >= 0 && location.x <= xMax && location.y >= 0 && location.y <= yMax
        && map[location.y][location.x] != '#';
  }

  record State(Point currentLocation,
               int distanceTraveled,
               Set<Point> possibleNextLocations,
               List<Point> path) {

  }
}
