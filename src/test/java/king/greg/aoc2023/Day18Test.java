package king.greg.aoc2023;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day18Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day18/sample1.txt"))
            .toURI()));
    final Day18 day18 = new Day18(lines);
    Assertions.assertThat(day18.lavaArea()).isEqualTo(62);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day18/input.txt"))
            .toURI()));
    final Day18 day18 = new Day18(lines);
    Assertions.assertThat(day18.lavaArea()).isEqualTo(49061);
  }

  @Test
  void testSample2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day18/sample1.txt"))
            .toURI()));
    final Day18 day18 = new Day18(lines);
    Assertions.assertThat(day18.lavaArea(true)).isEqualTo(952408144115L);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day18/input.txt"))
            .toURI()));
    final Day18 day18 = new Day18(lines);
    Assertions.assertThat(day18.lavaArea(true)).isEqualTo(92556825427032L);
  }
}