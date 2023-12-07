package king.greg.aoc2023;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day07Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day07/sample1.txt"))
            .toURI()));
    final Day07 day07 = new Day07(lines);
    Assertions.assertThat(day07.totalWinnings()).isEqualTo(6440);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day07/input.txt"))
            .toURI()));
    final Day07 day07 = new Day07(lines);
    Assertions.assertThat(day07.totalWinnings()).isEqualTo(250347426);
  }

  @Test
  void testSample2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day07/sample1.txt"))
            .toURI()));
    final Day07 day07 = new Day07(lines);
    Assertions.assertThat(day07.totalWinningsWithJokers()).isEqualTo(5905);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day07/input.txt"))
            .toURI()));
    final Day07 day07 = new Day07(lines);
    Assertions.assertThat(day07.totalWinningsWithJokers()).isEqualTo(251224870);
  }
}