package king.greg.aoc2023;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day12 {

  private final List<String> lines;
  private final Map<Point, Long> memo;

  Day12(final List<String> lines) {
    this.lines = lines;
    memo = new HashMap<>();
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

    return countArrangements(springsBuilder.toString(), 0, damages, 0);
  }

  private long countArrangements(final String springs, final int springIndex,
      final List<Integer> damages, final int damageIndex) {
    if (damageIndex == damages.size()) {
      return 1;
    }
    var memoed = memo.get(new Point(springIndex, damageIndex));
    if (null != memoed) {
      return memoed;
    }

    long count = 0;
    for (int i = springIndex; i <= springs.length() - damages.get(damageIndex); i++) {

      if (rightBoundaryValid(springs, i, damages, damageIndex) &&
          interiorSequenceValid(springs, i, damages, damageIndex) &&
          endValid(springs, i, damages, damageIndex)) {
        count += countArrangements(springs, i + damages.get(damageIndex) + 1, damages,
            damageIndex + 1);
      }

      if (springs.charAt(i) == '#') { // left side is locked
        break;
      }
    }

    memo.put(new Point(springIndex, damageIndex), count);

    return count;
  }

  private boolean rightBoundaryValid(final String springs, final int springIndex,
      final List<Integer> damages, final int damageIndex) {
    return !(springIndex + damages.get(damageIndex) < springs.length()
        && springs.charAt(springIndex + damages.get(damageIndex)) == '#');
  }

  private boolean interiorSequenceValid(final String springs, final int springIndex,
      final List<Integer> damages, final int damageIndex) {
    for (int i = 0; i < damages.get(damageIndex); i++) {
      if (springs.charAt(springIndex + i) == '.') {
        return false;
      }
    }
    return true;
  }

  private boolean endValid(final String springs, final int springIndex, final List<Integer> damages,
      final int damageIndex) {
    if (damageIndex == damages.size() - 1) {
      for (int i = springIndex + damages.get(damageIndex) + 1; i < springs.length(); i++) {
        if (springs.charAt(i) == '#') {
          return false;
        }
      }
    }
    return true;
  }
}
