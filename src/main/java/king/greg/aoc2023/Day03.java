package king.greg.aoc2023;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;

public class Day03 {

  final Set<Point> symbols = new HashSet<>();
  final Set<Point> gears = new HashSet<>();
  final List<Pair<Long, Set<Point>>> parts = new ArrayList<>();

  Day03(final List<String> lines) {
    for (var y = 0; y < lines.size(); y++) {
      var currentLine = lines.get(y).toCharArray();
      long currentNumber = 0;
      Set<Point> adjacencies = new HashSet<>();
      for (var x = 0; x < currentLine.length; x++) {
        var currentCharacter = currentLine[x];
        if (currentCharacter >= '0' && currentCharacter <= '9') {
          adjacencies.addAll(neighbors(x, y));
          currentNumber = (currentNumber * 10) + (currentCharacter - '0');
        } else {
          if (currentNumber > 0) {
            parts.add(Pair.of(currentNumber, adjacencies));
            currentNumber = 0;
            adjacencies = new HashSet<>();
          }
          if (currentCharacter == '*') {
            gears.add(new Point(x, y));
          }
          if (currentCharacter != '.') {
            symbols.add(new Point(x, y));
          }
        }
      }
      if (currentNumber > 0) {
        parts.add(Pair.of(currentNumber, adjacencies));
      }
    }
  }

  public long partSum() {
    long partSum = 0;
    for (var part : parts) {
      if (!Collections.disjoint(symbols, part.getRight())) {
        partSum += part.getLeft();
      }
    }
    return partSum;
  }

  public long gearRatioSum() {
    long gearRatioSum = 0;
    for (final var gear : gears) {
      long ratio = 1;
      int count = 0;
      for (final var part : parts) {
        if (part.getRight().contains(gear)) {
          count++;
          if (count > 2) {
            break;
          }
          ratio *= part.getLeft();
        }
      }
      if (count == 2) {
        gearRatioSum += ratio;
      }
    }
    return gearRatioSum;
  }

  private Set<Point> neighbors(final int x, final int y) {
    final Set<Point> neighbors = new HashSet<>();
    for (var currX = x - 1; currX <= x + 1; currX++) {
      for (var currY = y - 1; currY <= y + 1; currY++) {
        neighbors.add(new Point(currX, currY));
      }
    }
    return neighbors;
  }
}
