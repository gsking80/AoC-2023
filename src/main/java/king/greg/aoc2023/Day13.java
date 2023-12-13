package king.greg.aoc2023;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.lang3.tuple.Pair;

public class Day13 {

  private final List<Set<Point>> maps;

  Day13(final List<String> lines) {
    maps = new ArrayList<>();
    Set<Point> currentMap = new HashSet<>();
    int y = 0;
    for (final var line : lines) {
      if (line.isBlank()) {
        maps.add(currentMap);
        currentMap = new HashSet<>();
        y = 0;
      } else {
        for (int x = 0; x < line.length(); x++) {
          if (line.charAt(x) == '#') {
            currentMap.add(new Point(x, y));
          }
        }
        y++;
      }
    }
    maps.add(currentMap);
  }

  public int totalReflectionScore() {
    int reflectionScore = 0;
    for (final var map : maps) {
      reflectionScore += reflectionScore(map);
    }
    return reflectionScore;
  }

  public int totalSmudgeReflectionScore() {
    int reflectionScore = 0;
    for (final var map : maps) {
      reflectionScore += smudgeReflectionScore(map);
    }
    return reflectionScore;
  }

  private int reflectionScore(final Set<Point> map) {
    final int xMax = map.stream().mapToInt(p -> p.x).max().orElse(0);
    final int yMax = map.stream().mapToInt(p -> p.y).max().orElse(0);
    for (int i = 1; i <= Math.max(xMax, yMax); i++) {
      if (i <= xMax && verticalTest(i, map, xMax)) {
        return i;
      } else if (i <= yMax && horizontalTest(i, map, yMax)) {
        return i * 100;
      }
    }
    return 0;
  }

  private int smudgeReflectionScore(final Set<Point> map) {
    final int xMax = map.stream().mapToInt(p -> p.x).max().orElse(0);
    final int yMax = map.stream().mapToInt(p -> p.y).max().orElse(0);
    for (int i = 1; i <= Math.max(xMax, yMax); i++) {
      if (i <= xMax && verticalSmudgeTest(i, map, xMax)) {
        return i;
      } else if (i <= yMax && horizontalSmudgeTest(i, map, yMax)) {
        return i * 100;
      }
    }
    return 0;
  }

  private boolean verticalTest(final int xTest, final Set<Point> map, final int xMax) {
    final var reflections = reflectVertical(xTest, map, xMax);
    return reflections.getLeft().equals(reflections.getRight());
  }

  private boolean horizontalTest(final int yTest, final Set<Point> map, final int yMax) {
    final var reflections = reflectHorizontal(yTest, map, yMax);
    return reflections.getLeft().equals(reflections.getRight());
  }

  private boolean verticalSmudgeTest(final int xTest, final Set<Point> map, final int xMax) {
    final var reflections = reflectVertical(xTest, map, xMax);
    return SetUtils.disjunction(reflections.getLeft(), reflections.getRight()).size() == 1;
  }

  private boolean horizontalSmudgeTest(final int yTest, final Set<Point> map, final int yMax) {
    final var reflections = reflectHorizontal(yTest, map, yMax);
    return SetUtils.disjunction(reflections.getLeft(), reflections.getRight()).size() == 1;
  }

  private Pair<Set<Point>, Set<Point>> reflectVertical(final int xTest, final Set<Point> map,
      final int xMax) {
    final Set<Point> left = new HashSet<>();
    final Set<Point> rightReflected = new HashSet<>();

    final var leftMinBound = xTest <= xMax / 2 ? 0 : 2 * xTest - xMax - 1;
    final var leftMaxBound = xTest - 1;
    final var rightMinBound = xTest;
    final var rightMaxBound = xTest <= xMax / 2 ? xTest * 2 - 1 : xMax;
    for (final var point : map) {
      if (leftMinBound <= point.x && point.x <= leftMaxBound) {
        left.add(point);
      } else if (rightMinBound <= point.x && point.x <= rightMaxBound) {
        rightReflected.add(new Point((2 * xTest - point.x) - 1, point.y));
      }
    }
    return Pair.of(left, rightReflected);
  }

  private Pair<Set<Point>, Set<Point>> reflectHorizontal(final int yTest, final Set<Point> map,
      final int yMax) {
    final Set<Point> top = new HashSet<>();
    final Set<Point> bottomReflected = new HashSet<>();

    final var topMinBound = yTest <= yMax / 2 ? 0 : 2 * yTest - yMax - 1;
    final var topMaxBound = yTest - 1;
    final var bottomMinBound = yTest;
    final var bottomMaxBound = yTest <= yMax / 2 ? yTest * 2 - 1 : yMax;
    for (final var point : map) {
      if (topMinBound <= point.y && point.y <= topMaxBound) {
        top.add(point);
      } else if (bottomMinBound <= point.y && point.y <= bottomMaxBound) {
        bottomReflected.add(new Point(point.x, (2 * yTest - point.y) - 1));
      }
    }

    return Pair.of(top, bottomReflected);
  }
}
