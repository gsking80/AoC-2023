package king.greg.aoc2023;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day10Test {

  @Test
  void testSample1a() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day10/sample1.txt"))
            .toURI()));
    final Day10 day10 = new Day10(lines, 'F');
    Assertions.assertThat(day10.farthestSteps()).isEqualTo(4);
  }

  @Test
  void testSample1b() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day10/sample2.txt"))
            .toURI()));
    final Day10 day10 = new Day10(lines, 'F');
    Assertions.assertThat(day10.farthestSteps()).isEqualTo(8);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day10/input.txt"))
            .toURI()));
    final Day10 day10 = new Day10(lines, 'J');
    Assertions.assertThat(day10.farthestSteps()).isEqualTo(6927);
  }

  @Test
  void testSample2a() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day10/sample3.txt"))
            .toURI()));
    final Day10 day10 = new Day10(lines, 'F');
    Assertions.assertThat(day10.enclosedArea()).isEqualTo(4);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day10/input.txt"))
            .toURI()));
    final Day10 day10 = new Day10(lines, 'J');
    Assertions.assertThat(day10.enclosedArea()).isEqualTo(467);
  }
}