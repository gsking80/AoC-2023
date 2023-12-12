package king.greg.aoc2023;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class Day12 {

  private final List<String> lines;

  Day12(final List<String> lines) {
    this.lines = lines;
  }

  public int totalArrangements() {
    int totalArrangements = 0;
    for (final var line : lines) {
      totalArrangements += arrangements(line);
    }
    return totalArrangements;
  }

  private static int arrangements(final String line) {
    int arrangements = 0;
    final var parts = line.split(" ");
    final var damages = Arrays.stream(parts[1].split(",")).map(Integer::valueOf).toList();
    final Deque<String> possibilities = new ArrayDeque<>();
    possibilities.add(parts[0]);
    while(!possibilities.isEmpty()) {
      final var current = possibilities.removeFirst();
      final var firstDecision = current.indexOf('?');
      if(firstDecision == -1) {
        if(validConfiguration(current, damages)){
          arrangements++;
        }
      } else {
        possibilities.add(current.replaceFirst("\\?", "."));
        possibilities.add(current.replaceFirst("\\?", "#"));
      }
    }


    return arrangements;
  }

  private static boolean validConfiguration(final String configuration, final List<Integer> damages) {
    final var sections = StringUtils.split(configuration, '.');
    if (sections.length != damages.size()) {
      return false;
    }
    for(int i = 0; i < sections.length; i++) {
      if(sections[i].length() != damages.get(i)) {
        return false;
      }
    }
    return true;
  }

}
