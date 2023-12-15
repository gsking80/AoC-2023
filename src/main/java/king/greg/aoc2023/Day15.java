package king.greg.aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.lang3.tuple.Pair;

public class Day15 {

  private final List<String> steps;

  Day15(final List<String> lines) {
    this.steps = new ArrayList<>();
    for (final var line : lines) {
      final var parts = line.split(",");
      this.steps.addAll(Arrays.asList(parts));
    }
  }

  final int hashSum() {
    int hashSum = 0;
    for (final var step : steps) {
      hashSum += hash(step);
    }
    return hashSum;
  }

  final int focusingPower() {
    final List<Pair<List<String>, Map<String, Integer>>> boxes = initializeBoxes();
    final Pattern pattern = Pattern.compile("(\\w+)([-=])(\\w?)");
    for (final var step : steps) {
      final var matches = pattern.matcher(step);
      if (matches.matches()) {
        final var label = matches.group(1);
        final var box = boxes.get(hash(label));
        switch (matches.group(2)) {
          case "=" -> {
            if (!box.getLeft().contains(label)) {
              box.getLeft().add(label);
            }
            box.getRight().put(label, Integer.parseInt(matches.group(3)));
          }
          case "-" -> box.getLeft().remove(label);
          default -> throw new IllegalArgumentException(matches.group(2));
        }
      } else {
        throw new IllegalArgumentException(step);
      }
    }
    return boxScore(boxes);
  }

  private int boxScore(final List<Pair<List<String>, Map<String, Integer>>> boxes) {
    int boxScore = 0;
    for (int i = 0; i < 256; i++) {
      final var box = boxes.get(i);
      for (int j = 0; j < box.getLeft().size(); j++) {
        boxScore += (i + 1) * (j + 1) * box.getRight().get(box.getLeft().get(j));
      }
    }
    return boxScore;
  }

  private List<Pair<List<String>, Map<String, Integer>>> initializeBoxes() {
    final List<Pair<List<String>, Map<String, Integer>>> boxes = new ArrayList<>();
    for (int i = 0; i < 256; i++) {
      final List<String> boxContents = new ArrayList<>();
      final Map<String, Integer> boxMap = new HashMap<>();
      boxes.add(Pair.of(boxContents, boxMap));
    }
    return boxes;
  }

  private int hash(final String input) {
    int value = 0;
    for (final var character : input.toCharArray()) {
      value += character;
      value *= 17;
      value = value % 256;
    }
    return value;
  }
}
