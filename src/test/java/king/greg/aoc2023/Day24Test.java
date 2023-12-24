package king.greg.aoc2023;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

class Day24Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day24/sample1.txt"))
            .toURI()));
    final Day24 day24 = new Day24(lines);
    Assertions.assertThat(day24.intersections2D(7, 27)).isEqualTo(2);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day24/input.txt"))
            .toURI()));
    final Day24 day24 = new Day24(lines);
    Assertions.assertThat(day24.intersections2D(200000000000000L, 400000000000000L))
        .isEqualTo(31208);
  }

  @Ignore
  @Test
  void testSample2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day24/sample1.txt"))
            .toURI()));
    final Day24 day24 = new Day24(lines);
    Assertions.assertThat(day24.stoneThrow()).isEqualTo(47);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day24/input.txt"))
            .toURI()));
    final Day24 day24 = new Day24(lines);
    Assertions.assertThat(day24.stoneThrow()).isEqualTo(580043851566574L);
  }
}