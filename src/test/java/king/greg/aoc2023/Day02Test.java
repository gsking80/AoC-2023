package king.greg.aoc2023;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day02Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day02/sample1.txt"))
            .toURI()));
    final Day02 day02 = new Day02(lines);
    Assertions.assertThat(day02.possibleGamesScore(12, 13, 14)).isEqualTo(8);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day02/input.txt"))
            .toURI()));
    final Day02 day02 = new Day02(lines);
    Assertions.assertThat(day02.possibleGamesScore(12, 13, 14)).isEqualTo(2265);
  }

  @Test
  void testSample2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day02/sample1.txt"))
            .toURI()));
    final Day02 day02 = new Day02(lines);
    Assertions.assertThat(day02.powerSum()).isEqualTo(2286);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day02/input.txt"))
            .toURI()));
    final Day02 day02 = new Day02(lines);
    Assertions.assertThat(day02.powerSum()).isEqualTo(64097);
  }
}