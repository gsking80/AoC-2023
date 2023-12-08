package king.greg.aoc2023;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.tuple.Pair;

public class Day08 {

  private final String directions;
  private final Map<String, Pair<String, String>> map;

  Day08(final List<String> lines) {
    this.directions = lines.get(0);
    map = new HashMap<>();
    for (var i = 2; i < lines.size(); i++) {
      var parts = lines.get(i).split(" = \\(|, |\\)");
      map.put(parts[0], Pair.of(parts[1], parts[2]));
    }
  }

  private static long lcm(final long[] array) {
    long lcm = 1;
    for (long value : array) {
      lcm = lcm(lcm, value);
    }
    return lcm;
  }

  private static long lcm(final long a, final long b) {
    if (a == 0 || b == 0) {
      return 0;
    }
    return ((a * b) / gcd(a, b));
  }

  private static long gcd(long a, long b) {
    if (b == 0) {
      return 0;
    } else if (a < b) {
      return gcd(b, a);
    } else {
      while (b > 0) {
        long tempA = a;
        a = b;
        b = tempA % b;
      }
      return a;
    }
  }

  public long totalSteps(final String start, final String end) {
    int steps = 0;
    int directionIndex = 0;
    String currentLocation = start;
    while (true) {
      steps++;
      var fork = map.get(currentLocation);
      if (directions.charAt(directionIndex) == 'L') {
        currentLocation = fork.getLeft();
      } else {
        currentLocation = fork.getRight();
      }
      if (currentLocation.matches(end)) {
        return steps;
      }
      directionIndex++;
      if (directionIndex >= directions.length()) {
        directionIndex = 0;
      }
    }
  }

  public long totalGhostSteps(final String start, final String end) {
    final List<String> ghostLocations = new ArrayList<>();
    for (final var location : map.keySet()) {
      if (location.matches(start)) {
        ghostLocations.add(location);
      }
    }
    long[] periods = new long[ghostLocations.size()];
    for (var i = 0; i < ghostLocations.size(); i++) {
      // Luckily they were nice enough to keep this simple
      periods[i] = totalSteps(ghostLocations.get(i), end);
    }
    return lcm(periods);
  }
}
