package king.greg.aoc2023;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day12 {

  private static final char DAMAGED_SPRING = '#';
  private static final char OPERATIONAL_SPRING = '.';
  private final List<String> lines;
  private final Map<Point, Long> memo;

  Day12(final List<String> lines) {
    this.lines = lines;
    memo = new HashMap<>();
  }

  private static boolean rightBoundaryValid(final char[] springs, final int springIndex,
      final int damageLength) {
    return !(springIndex + damageLength < springs.length
        && springs[springIndex + damageLength] == DAMAGED_SPRING);
  }

  private static boolean interiorSequenceValid(final char[] springs, final int springIndex,
      final int damageLength) {
    for (int i = 0; i < damageLength; i++) {
      if (springs[springIndex + i] == OPERATIONAL_SPRING) {
        return false;
      }
    }
    return true;
  }

  private static boolean endValid(final char[] springs, final int springIndex,
      final List<Integer> damages,
      final int damageIndex) {
    if (damageIndex == damages.size() - 1) {
      for (int i = springIndex + damages.get(damageIndex) + 1; i < springs.length; i++) {
        if (springs[i] == DAMAGED_SPRING) {
          return false;
        }
      }
    }
    return true;
  }

  public long totalArrangements(final int folds) {
    long totalArrangements = 0;
    for (final var line : lines) {
      totalArrangements += arrangements(line, folds);
    }
    return totalArrangements;
  }

  private long arrangements(final String line, final int folds) {
    memo.clear();
    final var parts = line.split(" ");
    final var foldedDamages = Arrays.stream(parts[1].split(",")).map(Integer::valueOf).toList();
    final StringBuilder springsBuilder = new StringBuilder();
    final List<Integer> damages = new ArrayList<>();
    for (var i = 0; i < folds - 1; i++) {
      springsBuilder.append(parts[0]).append("?");
      damages.addAll(foldedDamages);
    }
    springsBuilder.append(parts[0]);
    damages.addAll(foldedDamages);

    return countArrangements(springsBuilder.toString().toCharArray(), 0, damages, 0);
  }

  private long countArrangements(final char[] springs, final int springIndex,
      final List<Integer> damages, final int damageIndex) {
    if (damageIndex == damages.size()) {
      return 1;
    }
    var memoed = memo.get(new Point(springIndex, damageIndex));
    if (null != memoed) {
      return memoed;
    }

    long count = 0;
    final int damageLength = damages.get(damageIndex);
    for (int i = springIndex; i <= springs.length - damageLength; i++) {

      if (rightBoundaryValid(springs, i, damageLength) &&
          interiorSequenceValid(springs, i, damageLength) &&
          endValid(springs, i, damages, damageIndex)) {
        count += countArrangements(springs, i + damages.get(damageIndex) + 1, damages,
            damageIndex + 1);
      }

      if (springs[i] == DAMAGED_SPRING) { // left side is locked
        break;
      }
    }

    memo.put(new Point(springIndex, damageIndex), count);
    return count;
  }
}
