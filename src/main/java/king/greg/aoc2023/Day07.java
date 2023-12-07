package king.greg.aoc2023;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day07 {

  private final List<Hand> hands;

  Day07(final List<String> lines) {
    hands = new ArrayList<>();
    for (final var line : lines) {
      final var parts = line.split(" ");
      hands.add(new Hand(parts[0], Long.parseLong(parts[1])));
    }
  }

  private static int compareHands(final String cards1, final List<Long> counts1,
      final String cards2, final List<Long> counts2, final Map<Character, Integer> cardValues) {
    for (var i = 0; i < counts1.size(); i++) {
      if (counts1.get(i) < counts2.get(i)) {
        return -1;
      } else if (counts2.get(i) < counts1.get(i)) {
        return 1;
      }
    }
    for (var i = 0; i < cards1.length(); i++) {
      final var value1 = cardValues.get(cards1.charAt(i));
      final var value2 = cardValues.get(cards2.charAt(i));
      if (value1 < value2) {
        return -1;
      } else if (value2 < value1) {
        return 1;
      }
    }
    return 0;
  }

  public final long totalWinnings() {
    hands.sort(new HandComparator());
    long totalWinnings = 0;
    long rank = 1;
    for (final var hand : hands) {
      totalWinnings += hand.getWager() * rank;
      rank++;
    }
    return totalWinnings;
  }

  public final long totalWinningsWithJokers() {
    hands.sort(new JokerHandComparator());
    long totalWinnings = 0;
    long rank = 1;
    for (final var hand : hands) {
      totalWinnings += hand.getWager() * rank;
      rank++;
    }
    return totalWinnings;
  }

  private static class Hand {

    private final String cards;
    private final long wager;
    private final Map<Character, Long> frequencyMap;

    public Hand(String cards, long wager) {
      this.cards = cards;
      this.wager = wager;
      frequencyMap = cards.chars().mapToObj(c -> (char) c)
          .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public String getCards() {
      return cards;
    }

    public long getWager() {
      return wager;
    }

    public Map<Character, Long> getFrequencyMap() {
      return frequencyMap;
    }
  }

  private static class HandComparator implements Comparator<Hand> {

    private static final Map<Character, Integer> cardValues = new HashMap<>();

    static {
      cardValues.put('A', 14);
      cardValues.put('K', 13);
      cardValues.put('Q', 12);
      cardValues.put('J', 11);
      cardValues.put('T', 10);
      cardValues.put('9', 9);
      cardValues.put('8', 8);
      cardValues.put('7', 7);
      cardValues.put('6', 6);
      cardValues.put('5', 5);
      cardValues.put('4', 4);
      cardValues.put('3', 3);
      cardValues.put('2', 2);
    }

    @Override
    public int compare(Hand h1, Hand h2) {
      var counts1 = h1.getFrequencyMap().values().stream().sorted(Comparator.reverseOrder())
          .toList();
      var counts2 = h2.getFrequencyMap().values().stream().sorted(Comparator.reverseOrder())
          .toList();

      return compareHands(h1.getCards(), counts1, h2.getCards(), counts2, cardValues);
    }
  }

  private static class JokerHandComparator implements Comparator<Hand> {

    private static final Map<Character, Integer> cardValues = new HashMap<>();

    static {
      cardValues.put('A', 14);
      cardValues.put('K', 13);
      cardValues.put('Q', 12);
      cardValues.put('T', 10);
      cardValues.put('9', 9);
      cardValues.put('8', 8);
      cardValues.put('7', 7);
      cardValues.put('6', 6);
      cardValues.put('5', 5);
      cardValues.put('4', 4);
      cardValues.put('3', 3);
      cardValues.put('2', 2);
      cardValues.put('J', 1);
    }

    @Override
    public int compare(Hand h1, Hand h2) {
      var counts1 = wilds(h1.getFrequencyMap()).values().stream().sorted(Comparator.reverseOrder())
          .toList();
      var counts2 = wilds(h2.getFrequencyMap()).values().stream().sorted(Comparator.reverseOrder())
          .toList();

      return compareHands(h1.getCards(), counts1, h2.getCards(), counts2, cardValues);
    }

    private Map<Character, Long> wilds(final Map<Character, Long> frequencyMap) {
      var jokerCount = frequencyMap.get('J');
      if (null != jokerCount && jokerCount != 5) {
        frequencyMap.remove('J');
        final var maxCard = Collections.max(frequencyMap.entrySet(), Map.Entry.comparingByValue())
            .getKey();
        frequencyMap.put(maxCard, frequencyMap.get(maxCard) + jokerCount);
      }
      return frequencyMap;
    }
  }
}
