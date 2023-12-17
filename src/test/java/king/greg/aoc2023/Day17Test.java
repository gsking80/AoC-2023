package king.greg.aoc2023;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day17Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day17/sample1.txt"))
            .toURI()));
    final Day17 day17 = new Day17(lines);
    Assertions.assertThat(day17.minimumHeatLoss()).isEqualTo(102);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day17/input.txt"))
            .toURI()));
    final Day17 day17 = new Day17(lines);
    Assertions.assertThat(day17.minimumHeatLoss()).isEqualTo(1128);
  }

  @Test
  void testSample2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day17/sample1.txt"))
            .toURI()));
    final Day17 day17 = new Day17(lines);
    Assertions.assertThat(day17.minimumHeatLoss(4, 10, 4)).isEqualTo(94);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day17/input.txt"))
            .toURI()));
    final Day17 day17 = new Day17(lines);
    Assertions.assertThat(day17.minimumHeatLoss(4, 10, 4)).isEqualTo(1268);
  }
}