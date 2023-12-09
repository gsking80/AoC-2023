package king.greg.aoc2023;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day09Test {

  @Test
  void testSample1a() {
    final Day09 day09 = new Day09(List.of("0 3 6 9 12 15"));
    Assertions.assertThat(day09.extrapolatedSum(false)).isEqualTo(18);
  }

  @Test
  void testSample1b() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day09/sample1.txt"))
            .toURI()));
    final Day09 day09 = new Day09(lines);
    Assertions.assertThat(day09.extrapolatedSum(false)).isEqualTo(114);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day09/input.txt"))
            .toURI()));
    final Day09 day09 = new Day09(lines);
    Assertions.assertThat(day09.extrapolatedSum(false)).isEqualTo(1916822650);
  }

  @Test
  void testSample2a() {
    final Day09 day09 = new Day09(List.of("10 13 16 21 30 45"));
    Assertions.assertThat(day09.extrapolatedSum(true)).isEqualTo(5);
  }

  @Test
  void testSample2b() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day09/sample1.txt"))
            .toURI()));
    final Day09 day09 = new Day09(lines);
    Assertions.assertThat(day09.extrapolatedSum(true)).isEqualTo(2);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day09/input.txt"))
            .toURI()));
    final Day09 day09 = new Day09(lines);
    Assertions.assertThat(day09.extrapolatedSum(true)).isEqualTo(966);
  }
}