package king.greg.aoc2023;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day01Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day01/sample1.txt"))
            .toURI()));
    final Day01 day01 = new Day01(lines);
    Assertions.assertThat(day01.calibrate()).isEqualTo(142);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day01/input.txt"))
            .toURI()));
    final Day01 day01 = new Day01(lines);
    Assertions.assertThat(day01.calibrate()).isEqualTo(55712);
  }

  @Test
  void testSample2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day01/sample2.txt"))
            .toURI()));
    final Day01 day01 = new Day01(lines);
    Assertions.assertThat(day01.calibrateWithWords()).isEqualTo(281);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day01/input.txt"))
            .toURI()));
    final Day01 day01 = new Day01(lines);
    Assertions.assertThat(day01.calibrateWithWords()).isEqualTo(55413);
  }
}