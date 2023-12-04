package king.greg.aoc2023;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Day04 {

  private final List<String> lines;

  Day04(final List<String> lines) {
    this.lines = lines;
  }

  public int pileScore() {
    int pileScore = 0;
    for (final var line : lines) {
      final var winnerCount = getWinnerCount(line);
      if (winnerCount > 0) {
        pileScore += Math.pow(2, (winnerCount - 1));
      }
    }
    return pileScore;
  }

  public int totalCards() {
    int[] cardCount = new int[lines.size()];
    for (var i = 0; i < lines.size(); i++) {
      cardCount[i]++;
      var copies = cardCount[i];
      final long winnerCount = getWinnerCount(lines.get(i));
      for (var j = 1; j <= winnerCount; j++) {
        cardCount[i + j] += copies;
      }
    }
    return IntStream.of(cardCount).sum();
  }

  private long getWinnerCount(final String scratcher) {
    final var parts = scratcher.split(": | +\\| +");
    final var winningNumbers = Arrays.asList(parts[1].split(" +"));
    final var myNumbers = Arrays.asList(parts[2].split(" +"));
    return winningNumbers.stream().filter(myNumbers::contains).count();
  }
}
