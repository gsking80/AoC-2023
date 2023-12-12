package king.greg.aoc2023;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day12Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day12/sample1.txt"))
            .toURI()));
    final Day12 day12 = new Day12(lines);
    Assertions.assertThat(day12.totalArrangements(1)).isEqualTo(21);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day12/input.txt"))
            .toURI()));
    final Day12 day12 = new Day12(lines);
    Assertions.assertThat(day12.totalArrangements(1)).isEqualTo(7236);
  }

  @Test
  void testSample2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day12/sample1.txt"))
            .toURI()));
    final Day12 day12 = new Day12(lines);
    Assertions.assertThat(day12.totalArrangements(5)).isEqualTo(525152);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day12/input.txt"))
            .toURI()));
    final Day12 day12 = new Day12(lines);
    Assertions.assertThat(day12.totalArrangements(5)).isEqualTo(11607695322318L);
  }
}