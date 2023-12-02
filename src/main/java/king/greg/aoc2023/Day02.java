package king.greg.aoc2023;

import java.util.List;

public class Day02 {

  private final List<String> lines;

  Day02(final List<String> lines) {
    this.lines = lines;
  }

  public int possibleGamesScore(final int redCubes, final int greenCubes, final int blueCubes) {
    var possibleGamesScore = 0;
    for (final var line : lines) {
      final var game = line.split("[:, ;]+");
      final var colorMax = colorMax(game);
      if (colorMax[0] <= redCubes && colorMax[1] <= greenCubes && colorMax[2] <= blueCubes) {
        possibleGamesScore += Integer.parseInt(game[1]);
      }
    }
    return possibleGamesScore;
  }

  final long powerSum() {
    long powerSum = 0;

    for (final var line : lines) {
      final var game = line.split("[:, ;]+");
      final var colorMax = colorMax(game);
      powerSum += (long) colorMax[0] * colorMax[1] * colorMax[2];
    }

    return powerSum;
  }

  private int[] colorMax(final String[] game) {
    final int[] colorMax = new int[3];
    for (var i = 2; i < game.length; i+=2){
      final int count = Integer.parseInt(game[i]);
      switch (game[i+1]) {
        case "red" -> {
          if (count > colorMax[0]) {
            colorMax[0] = count;
          }
        }
        case "green" -> {
          if (count > colorMax[1]) {
            colorMax[1] = count;
          }
        }
        case "blue" -> {
          if (count > colorMax[2]) {
            colorMax[2] = count;
          }
        }
        default -> throw new IllegalArgumentException(game[i+1]);
      }
    }
    return colorMax;
  }

}
