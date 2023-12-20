package king.greg.aoc2023;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day20Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day20/sample1.txt"))
            .toURI()));
    final Day20 day20 = new Day20(lines);
    Assertions.assertThat(day20.pulseMultiplier(1000)).isEqualTo(32000000);
  }

  @Test
  void testSample2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day20/sample2.txt"))
            .toURI()));
    final Day20 day20 = new Day20(lines);
    Assertions.assertThat(day20.pulseMultiplier(1000)).isEqualTo(11687500);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day20/input.txt"))
            .toURI()));
    final Day20 day20 = new Day20(lines);
    Assertions.assertThat(day20.pulseMultiplier(1000)).isEqualTo(777666211);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day20/input.txt"))
            .toURI()));
    final Day20 day20 = new Day20(lines);
    Assertions.assertThat(day20.pressesToHigh("zh")).isEqualTo(243081086866483L);
  }
}