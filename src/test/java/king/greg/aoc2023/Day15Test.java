package king.greg.aoc2023;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day15Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day15/sample1.txt"))
            .toURI()));
    final Day15 day15 = new Day15(lines);
    Assertions.assertThat(day15.hashSum()).isEqualTo(1320);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day15/input.txt"))
            .toURI()));
    final Day15 day15 = new Day15(lines);
    Assertions.assertThat(day15.hashSum()).isEqualTo(498538);
  }

  @Test
  void testSample2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day15/sample1.txt"))
            .toURI()));
    final Day15 day15 = new Day15(lines);
    Assertions.assertThat(day15.focusingPower()).isEqualTo(145);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day15/input.txt"))
            .toURI()));
    final Day15 day15 = new Day15(lines);
    Assertions.assertThat(day15.focusingPower()).isEqualTo(286278);
  }
}