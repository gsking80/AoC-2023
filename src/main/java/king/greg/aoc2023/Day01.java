package king.greg.aoc2023;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day01 {

  private static final Map<String, Integer> numbers = new HashMap<>();
  static {
    numbers.put("1", 1);
    numbers.put("2", 2);
    numbers.put("3", 3);
    numbers.put("4", 4);
    numbers.put("5", 5);
    numbers.put("6", 6);
    numbers.put("7", 7);
    numbers.put("8", 8);
    numbers.put("9", 9);
    numbers.put("one", 1);
    numbers.put("two", 2);
    numbers.put("three", 3);
    numbers.put("four", 4);
    numbers.put("five", 5);
    numbers.put("six", 6);
    numbers.put("seven", 7);
    numbers.put("eight", 8);
    numbers.put("nine", 9);
  }

  private final List<String> lines;

  Day01(final List<String> lines) {
    this.lines = lines;
  }

  public long calibrate() {
    var value = 0;

    for (final var line: lines){
      for (var i = 0; i < line.length(); i++) {
        final var character = line.charAt(i);
        if (character >= '0' && character <= '9') {
          value += (character - '0') * 10;
          break;
        }
      }
      for (var i = line.length() - 1; i >= 0; i--) {
        final var character = line.charAt(i);
        if (character >= '0' && character <= '9') {
          value += character - '0';
          break;
        }
      }
    }

    return value;
  }

  public long calibrateWithWords() {
    var value = 0;

    for (final var line: lines) {
      var firstIndex = line.length();
      var lastIndex = -1;
      var tens = 0;
      var ones = 0;
      for(final var entry : numbers.entrySet()) {
        final var digit = entry.getKey();
        var findFirst = line.indexOf(digit);
        if (findFirst != -1 && findFirst < firstIndex) {
          firstIndex = findFirst;
          tens = entry.getValue();
        }
        var findLast = line.lastIndexOf(digit);
        if(findLast != -1 && findLast > lastIndex) {
          lastIndex = findLast;
          ones = entry.getValue();
        }
      }
      value += (tens * 10) + ones;
    }

    return value;
  }

}
