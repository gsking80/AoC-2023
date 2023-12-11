package king.greg.aoc2023;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day11Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day11/sample1.txt"))
            .toURI()));
    final Day11 day11 = new Day11(lines, 2);
    Assertions.assertThat(day11.sumShortest()).isEqualTo(374);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day11/input.txt"))
            .toURI()));
    final Day11 day11 = new Day11(lines, 2);
    Assertions.assertThat(day11.sumShortest()).isEqualTo(9795148);
  }

  @Test
  void testSample2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day11/sample1.txt"))
            .toURI()));
    final Day11 day11 = new Day11(lines, 100);
    Assertions.assertThat(day11.sumShortest()).isEqualTo(8410);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day11/input.txt"))
            .toURI()));
    final Day11 day11 = new Day11(lines, 1000000);
    Assertions.assertThat(day11.sumShortest()).isEqualTo(650672493820L);
  }
}