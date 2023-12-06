package king.greg.aoc2023;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day06Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day06/sample1.txt"))
            .toURI()));
    final Day06 day06 = new Day06(lines);
    Assertions.assertThat(day06.totalWaysToWin()).isEqualTo(288);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day06/input.txt"))
            .toURI()));
    final Day06 day06 = new Day06(lines);
    Assertions.assertThat(day06.totalWaysToWin()).isEqualTo(608902);
  }

  @Test
  void testSample2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day06/sample1.txt"))
            .toURI()));
    final Day06 day06 = new Day06(lines);
    Assertions.assertThat(day06.totalWaysToWinKerning()).isEqualTo(71503);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day06/input.txt"))
            .toURI()));
    final Day06 day06 = new Day06(lines);
    Assertions.assertThat(day06.totalWaysToWinKerning()).isEqualTo(46173809);
  }
}