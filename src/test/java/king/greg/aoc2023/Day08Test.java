package king.greg.aoc2023;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day08Test {

  @Test
  void testSample1a() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day08/sample1.txt"))
            .toURI()));
    final Day08 day08 = new Day08(lines);
    Assertions.assertThat(day08.totalSteps("AAA", "ZZZ")).isEqualTo(2);
  }

  @Test
  void testSample1b() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day08/sample2.txt"))
            .toURI()));
    final Day08 day08 = new Day08(lines);
    Assertions.assertThat(day08.totalSteps("AAA", "ZZZ")).isEqualTo(6);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day08/input.txt"))
            .toURI()));
    final Day08 day08 = new Day08(lines);
    Assertions.assertThat(day08.totalSteps("AAA", "ZZZ")).isEqualTo(13771);
  }

  @Test
  void testSample2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day08/sample3.txt"))
            .toURI()));
    final Day08 day08 = new Day08(lines);
    Assertions.assertThat(day08.totalGhostSteps(".+A$", ".+Z$")).isEqualTo(6);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day08/input.txt"))
            .toURI()));
    final Day08 day08 = new Day08(lines);
    Assertions.assertThat(day08.totalGhostSteps(".+A$", ".+Z$")).isEqualTo(13129439557681L);
  }
}