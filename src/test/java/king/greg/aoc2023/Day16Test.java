package king.greg.aoc2023;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day16Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day16/sample1.txt"))
            .toURI()));
    final Day16 day16 = new Day16(lines);
    Assertions.assertThat(day16.energizedTiles()).isEqualTo(46);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day16/input.txt"))
            .toURI()));
    final Day16 day16 = new Day16(lines);
    Assertions.assertThat(day16.energizedTiles()).isEqualTo(8551);
  }

  @Test
  void testSample2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day16/sample1.txt"))
            .toURI()));
    final Day16 day16 = new Day16(lines);
    Assertions.assertThat(day16.maxEnergizedTiles()).isEqualTo(51);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day16/input.txt"))
            .toURI()));
    final Day16 day16 = new Day16(lines);
    Assertions.assertThat(day16.maxEnergizedTiles()).isEqualTo(8754);
  }
}