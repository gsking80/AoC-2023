package king.greg.aoc2023;

import java.awt.Point;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import org.apache.commons.lang3.tuple.Triple;

public class Day17 {

  private static final List<Point> directions = Arrays.asList(
      new Point(0, -1),
      new Point(1, 0),
      new Point(0, 1),
      new Point(-1, 0)
  );
  private final int[][] map;
  private final int xMax;
  private final int yMax;

  Day17(final List<String> lines) {
    map = new int[lines.size()][lines.get(0).length()];
    yMax = lines.size() - 1;
    xMax = lines.get(0).length() - 1;
    for (int y = 0; y < lines.size(); y++) {
      var line = lines.get(y).toCharArray();
      for (int x = 0; x < line.length; x++) {
        map[y][x] = line[x] - '0';
      }
    }
  }

  public int minimumHeatLoss() {
    return minimumHeatLoss(0, 3, 0);
  }

  public int minimumHeatLoss(final int minimumTurnVelocity, final int maximumVelocity,
      final int minimumExitVelocity) {
    Queue<Node> priorityQueue = initQueue();
    final Set<Triple<Point, Integer, Integer>> visited = new HashSet<>();
    Node current;
    final Point target = new Point(xMax, yMax);
    priorityQueue.add(new Node(0, 0, 0, 1, 0));
    priorityQueue.add(new Node(0, 0, 0, 2, 0));
    while (!priorityQueue.isEmpty()) {
      current = priorityQueue.remove();
      final Triple<Point, Integer, Integer> key = Triple.of(
          current.location,
          current.direction,
          current.velocity);
      if (visited.contains(key)) {
        continue;
      }
      visited.add(key);
      if (current.location.equals(target) && current.velocity >= minimumExitVelocity) {
        return current.heatLost;
      }
      priorityQueue.addAll(current.getNext(minimumTurnVelocity, maximumVelocity));
    }
    return -1;
  }

  private PriorityQueue<Node> initQueue() {
    return new PriorityQueue<>(10,
        (arg0, arg1) -> Comparator.comparingInt(Node::minimumTotalHeatLoss).compare(arg0, arg1));
  }

  class Node {

    final Point location;
    final int velocity;
    final int direction;
    final int heatLost;
    final int minimumStepsRemaining;
    final int minimumTotalHeatLoss;

    public Node(final int x, final int y, final int velocity, final int direction,
        final int heatLost) {
      this.location = new Point(x, y);
      this.velocity = velocity;
      this.direction = direction;
      this.heatLost = heatLost;
      this.minimumStepsRemaining = (xMax - x) + (yMax - y);
      this.minimumTotalHeatLoss = heatLost + minimumStepsRemaining;
    }

    public Set<Node> getNext(final int minimumTurnVelocity, final int maximumVelocity) {
      final Set<Node> next = new HashSet<>();
      for (int directionChoice = 0; directionChoice < 4; directionChoice++) {
        if (directionChoice == direction && velocity < maximumVelocity) {
          int xNext = location.x + directions.get(directionChoice).x;
          int yNext = location.y + directions.get(directionChoice).y;
          if (validLocation(xNext, yNext)) {
            next.add(new Node(xNext, yNext, velocity + 1, directionChoice,
                heatLost + map[yNext][xNext]));
          }
        } else if (Math.abs(directionChoice - direction) == 1 && velocity >= minimumTurnVelocity) {
          int xNext = location.x + directions.get(directionChoice).x;
          int yNext = location.y + directions.get(directionChoice).y;
          if (validLocation(xNext, yNext)) {
            next.add(new Node(xNext, yNext, 1, directionChoice, heatLost + map[yNext][xNext]));
          }
        }
      }
      return next;
    }

    public int minimumTotalHeatLoss() {
      return minimumTotalHeatLoss;
    }

    @Override
    public String toString() {
      return "Node{" +
          "location=" + location +
          ", velocity=" + velocity +
          ", direction=" + direction +
          ", heatLost=" + heatLost +
          ", minimumTotalHeatLoss=" + minimumTotalHeatLoss +
          '}';
    }

    private boolean validLocation(final int x, final int y) {
      return 0 <= x && x <= xMax && 0 <= y && y <= yMax;
    }
  }
}
