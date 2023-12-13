package king.greg.aoc2023;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day13Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day13/sample1.txt"))
            .toURI()));
    final Day13 day13 = new Day13(lines);
    Assertions.assertThat(day13.totalReflectionScore()).isEqualTo(405);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day13/input.txt"))
            .toURI()));
    final Day13 day13 = new Day13(lines);
    Assertions.assertThat(day13.totalReflectionScore()).isEqualTo(31265);
  }

  @Test
  void testSample2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day13/sample1.txt"))
            .toURI()));
    final Day13 day13 = new Day13(lines);
    Assertions.assertThat(day13.totalSmudgeReflectionScore()).isEqualTo(400);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day13/input.txt"))
            .toURI()));
    final Day13 day13 = new Day13(lines);
    Assertions.assertThat(day13.totalSmudgeReflectionScore()).isEqualTo(39359);
  }
}