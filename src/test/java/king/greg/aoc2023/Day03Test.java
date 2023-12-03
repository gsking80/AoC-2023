package king.greg.aoc2023;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day03Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day03/sample1.txt"))
            .toURI()));
    final Day03 day03 = new Day03(lines);
    Assertions.assertThat(day03.partSum()).isEqualTo(4361);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day03/input.txt"))
            .toURI()));
    final Day03 day03 = new Day03(lines);
    Assertions.assertThat(day03.partSum()).isEqualTo(529618);
  }

  @Test
  void testSample2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day03/sample1.txt"))
            .toURI()));
    final Day03 day03 = new Day03(lines);
    Assertions.assertThat(day03.gearRatioSum()).isEqualTo(467835);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day03/input.txt"))
            .toURI()));
    final Day03 day03 = new Day03(lines);
    Assertions.assertThat(day03.gearRatioSum()).isEqualTo(77509019);
  }
}