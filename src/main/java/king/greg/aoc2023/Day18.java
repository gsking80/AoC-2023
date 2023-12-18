package king.greg.aoc2023;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Day18 {

  private final List<String> instructions;

  Day18(final List<String> lines) {
    this.instructions = lines;
  }

  public long lavaArea() {
    return lavaArea(false);
  }

  public long lavaArea(final boolean part2) {
    final List<Point> trench = new ArrayList<>();
    long trenchBoundary = 0;
    Point current = new Point(0, 0);
    trench.add(current);
    for (final var instruction : instructions) {
      final var parts = instruction.split(" ");
      final char direction = part2 ? parts[2].charAt(7) : parts[0].charAt(0);
      final int distance =
          part2 ? Integer.parseInt(parts[2].substring(2, 7), 16) : Integer.parseInt(parts[1]);
      trenchBoundary += distance;
      switch (direction) {
        case 'R', '0' -> {
          current = new Point(current.x + distance, current.y);
          trench.add(current);
        }
        case 'D', '1' -> {
          current = new Point(current.x, current.y - distance);
          trench.add(current);
        }
        case 'L', '2' -> {
          current = new Point(current.x - distance, current.y);
          trench.add(current);
        }
        case 'U', '3' -> {
          current = new Point(current.x, current.y + distance);
          trench.add(current);
        }
        default -> throw new UnsupportedOperationException(instruction);
      }
    }

    return totalArea(shoelaceArea(trench), trenchBoundary);
  }

  public long shoelaceArea(final List<Point> trench) {
    final int n = trench.size();
    long area = 0;
    for (int i = 0; i < n - 1; i++) {
      area +=
          (long) trench.get(i).x * trench.get(i + 1).y - (long) trench.get(i + 1).x * trench.get(
              i).y;
    }
    area = Math.abs(
        area + (long) trench.get(n - 1).x * trench.get(0).y - (long) trench.get(0).x * trench.get(
            n - 1).y) / 2;
    return area;
  }

  public long totalArea(final long shoeLaceArea, final long boundaryLength) {
    return shoeLaceArea + (boundaryLength / 2) + 1;
  }
}
