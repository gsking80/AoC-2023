package king.greg.aoc2023;

import java.util.List;

public class Day02 {

  private final List<String> lines;

  Day02(final List<String> lines) {
    this.lines = lines;
  }

  public int possibleGamesScore(final int redCubes, final int greenCubes, final int blueCubes) {
    var possibleGamesScore = 0;
    for (final var game : lines) {
      final var parts = game.split(":");
      final var gameId = Integer.parseInt(parts[0].split(" ")[1]);
      final var pulls = parts[1].split(";");
      var validGame = true;
      for (final var pull : pulls) {
        final var colors = pull.split(",");
        for (final var color : colors) {
          final var colorPieces = color.split(" ");
          final var count = Integer.parseInt(colorPieces[1]);
          switch (colorPieces[2]) {
            case "red" -> {
              if (count > redCubes) {
                validGame = false;
              }
            }
            case "green" -> {
              if (count > greenCubes) {
                validGame = false;
              }
            }
            case "blue" -> {
              if (count > blueCubes) {
                validGame = false;
              }
            }
            default -> throw new IllegalArgumentException(colorPieces[2]);
          }
          if (!validGame) {
            break;
          }
        }
        if (!validGame) {
          break;
        }
      }
      if (validGame) {
        possibleGamesScore += gameId;
      }
    }

    return possibleGamesScore;
  }

  final long powerSum() {
    long powerSum = 0;

    for (final var game : lines) {
      final var parts = game.split(":");
      final var pulls = parts[1].split(";");
      var minRed = 0;
      var minGreen = 0;
      var minBlue = 0;
      for (final var pull : pulls) {
        final var colors = pull.split(",");
        for (final var color : colors) {
          final var colorPieces = color.split(" ");
          final var count = Integer.parseInt(colorPieces[1]);
          switch (colorPieces[2]) {
            case "red" -> {
              if (count > minRed) {
                minRed = count;
              }
            }
            case "green" -> {
              if (count > minGreen) {
                minGreen = count;
              }
            }
            case "blue" -> {
              if (count > minBlue) {
                minBlue = count;
              }
            }
            default -> throw new IllegalArgumentException(colorPieces[2]);
          }
        }
      }
      powerSum += (long) minRed * minGreen * minBlue;
    }

    return powerSum;
  }

}
