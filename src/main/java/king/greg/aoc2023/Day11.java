package king.greg.aoc2023;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;

public class Day11 {

  private final List<Pair<Long, Long>> galaxies;

  Day11(final List<String> lines, final long expansionSize) {
    final Set<Point> compactGalaxies = new HashSet<>();
    final Set<Integer> yValues = new HashSet<>();
    final Set<Integer> xValues = new HashSet<>();
    for (int y = 0; y < lines.size(); y++) {
      final var points = lines.get(y).toCharArray();
      for (int x = 0; x < points.length; x++) {
        if (points[x] == '#') {
          compactGalaxies.add(new Point(x, y));
          yValues.add(y);
          xValues.add(x);
        }
      }
    }
    final List<Integer> xSorted = xValues.stream().sorted().toList();
    final List<Integer> ySorted = yValues.stream().sorted().toList();
    final HashMap<Integer, Long> xTranslations = new HashMap<>();
    final HashMap<Integer, Long> yTranslations = new HashMap<>();

    galaxies = new ArrayList<>();

    for (final var galaxy : compactGalaxies) {
      final Long xNew = xTranslations.computeIfAbsent(galaxy.x,
          x -> Integer.toUnsignedLong(xSorted.indexOf(x))
              + (x - xSorted.indexOf(x)) * expansionSize);
      final Long yNew = yTranslations.computeIfAbsent(galaxy.y,
          y -> Integer.toUnsignedLong(ySorted.indexOf(y))
              + (y - ySorted.indexOf(y)) * expansionSize);
      galaxies.add(Pair.of(xNew, yNew));
    }
  }

  public long sumShortest() {
    long sumShortest = 0;
    for (var i = 0; i < galaxies.size() - 1; i++) {
      var galaxyI = galaxies.get(i);
      for (var j = i + 1; j < galaxies.size(); j++) {
        var galaxyJ = galaxies.get(j);
        sumShortest += Math.abs(galaxyI.getLeft() - galaxyJ.getLeft()) + Math.abs(
            galaxyI.getRight() - galaxyJ.getRight());
      }
    }
    return sumShortest;
  }
}
