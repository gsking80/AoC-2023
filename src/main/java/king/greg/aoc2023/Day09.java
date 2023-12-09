package king.greg.aoc2023;

import java.util.ArrayList;
import java.util.List;

public class Day09 {

  private final List<List<Long>> histories;

  Day09(final List<String> lines) {
    histories = new ArrayList<>();
    for (final var line : lines) {
      final var parts = line.split(" ");
      final List<Long> history = new ArrayList<>();
      for (final var part : parts) {
        history.add(Long.parseLong(part));
      }
      histories.add(history);
    }
  }

  public long extrapolatedSum(final boolean backwards) {
    long sum = 0;
    for (final var history : histories) {
      sum += extrapolate(history, backwards);
    }
    return sum;
  }

  private long extrapolate(final List<Long> history, final boolean backwards) {
    if (history.stream().allMatch(n -> n == 0)) {
      return 0;
    }
    final List<Long> nextHistory = new ArrayList<>();
    for (int i = 0; i < history.size() - 1; i++) {
      nextHistory.add(history.get(i + 1) - history.get(i));
    }
    final var extrapolated = extrapolate(nextHistory, backwards);
    return backwards ? (history.get(0) - extrapolated)
        : (history.get(history.size() - 1) + extrapolated);
  }
}
