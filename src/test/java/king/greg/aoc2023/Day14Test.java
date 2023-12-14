package king.greg.aoc2023;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day14Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day14/sample1.txt"))
            .toURI()));
    final Day14 day14 = new Day14(lines);
    Assertions.assertThat(day14.totalLoad()).isEqualTo(136);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day14/input.txt"))
            .toURI()));
    final Day14 day14 = new Day14(lines);
    Assertions.assertThat(day14.totalLoad()).isEqualTo(103333);
  }

  @Test
  void testSample2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day14/sample1.txt"))
            .toURI()));
    final Day14 day14 = new Day14(lines);
    Assertions.assertThat(day14.totalLoadAfterCycles(1000000000)).isEqualTo(64);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day14/input.txt"))
            .toURI()));
    final Day14 day14 = new Day14(lines);
    Assertions.assertThat(day14.totalLoadAfterCycles(1000000000)).isEqualTo(97241);
  }
}