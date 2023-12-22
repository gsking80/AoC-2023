package king.greg.aoc2023;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day22Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day22/sample1.txt"))
            .toURI()));
    final Day22 day22 = new Day22(lines);
    Assertions.assertThat(day22.safeToDisintegrate()).isEqualTo(5);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day22/input.txt"))
            .toURI()));
    final Day22 day22 = new Day22(lines);
    Assertions.assertThat(day22.safeToDisintegrate()).isEqualTo(461);
  }

  @Test
  void testSample2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day22/sample1.txt"))
            .toURI()));
    final Day22 day22 = new Day22(lines);
    Assertions.assertThat(day22.sumDisintegrationChains()).isEqualTo(7);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day22/input.txt"))
            .toURI()));
    final Day22 day22 = new Day22(lines);
    Assertions.assertThat(day22.sumDisintegrationChains()).isEqualTo(74074);
  }
}