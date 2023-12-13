package king.greg.aoc2023;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.lang3.tuple.Pair;

public class Day13 {

  private final List<Pair<Set<Point>, Point>> maps;

  Day13(final List<String> lines) {
    maps = new ArrayList<>();
    Set<Point> currentMap = new HashSet<>();
    int y = -1;
    int maxX = 0;
    for (final var line : lines) {
      if (line.isBlank()) {
        maps.add(Pair.of(currentMap, new Point(maxX, y)));
        currentMap = new HashSet<>();
        y = -1;
      } else {
        y++;
        for (int x = 0; x < line.length(); x++) {
          if (line.charAt(x) == '#') {
            currentMap.add(new Point(x, y));
          }
          maxX = x;
        }
      }
    }
    maps.add(Pair.of(currentMap, new Point(maxX, y)));
  }

  public int totalReflectionScore() {
    int reflectionScore = 0;
    for (final var mapEntry : maps) {
      reflectionScore += reflectionScore(mapEntry);
    }
    return reflectionScore;
  }

  public int totalSmudgeReflectionScore() {
    int reflectionScore = 0;
    for (final var mapEntry : maps) {
      reflectionScore += smudgeReflectionScore(mapEntry);
    }
    return reflectionScore;
  }

  private int reflectionScore(final Pair<Set<Point>, Point> mapEntry) {
    final int xMax = mapEntry.getRight().x;
    final int yMax = mapEntry.getRight().y;
    final var map = mapEntry.getLeft();
    for (int i = 1; i <= Math.max(xMax, yMax); i++) {
      if (i <= xMax && verticalTest(i, map, xMax)) {
        return i;
      } else if (i <= yMax && horizontalTest(i, map, yMax)) {
        return i * 100;
      }
    }
    return 0;
  }

  private int smudgeReflectionScore(final Pair<Set<Point>, Point> mapEntry) {
    final var xMax = mapEntry.getRight().x;
    final var yMax = mapEntry.getRight().y;
    final var map = mapEntry.getLeft();
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
    final var boundaries = setBoundaries(xTest, xMax);

    for (final var point : map) {
      if (boundaries[0] <= point.x && point.x <= boundaries[1]) {
        left.add(point);
      } else if (boundaries[2] <= point.x && point.x <= boundaries[3]) {
        rightReflected.add(new Point((2 * xTest - point.x) - 1, point.y));
      }
    }
    return Pair.of(left, rightReflected);
  }

  private Pair<Set<Point>, Set<Point>> reflectHorizontal(final int yTest, final Set<Point> map,
      final int yMax) {
    final Set<Point> top = new HashSet<>();
    final Set<Point> bottomReflected = new HashSet<>();
    final var boundaries = setBoundaries(yTest, yMax);

    for (final var point : map) {
      if (boundaries[0] <= point.y && point.y <= boundaries[1]) {
        top.add(point);
      } else if (boundaries[2] <= point.y && point.y <= boundaries[3]) {
        bottomReflected.add(new Point(point.x, (2 * yTest - point.y) - 1));
      }
    }

    return Pair.of(top, bottomReflected);
  }

  private int[] setBoundaries(final int test, final int max) {
    final int[] boundaries = new int[4];
    boundaries[0] = test <= (max / 2) ? 0 : ((2 * test) - max) - 1;
    boundaries[1] = test - 1;
    boundaries[2] = test;
    boundaries[3] = test <= (max / 2) ? (test * 2) - 1 : max;
    return boundaries;
  }
}
