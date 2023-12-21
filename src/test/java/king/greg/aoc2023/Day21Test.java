package king.greg.aoc2023;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day21Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day21/sample1.txt"))
            .toURI()));
    final Day21 day21 = new Day21(lines);
    Assertions.assertThat(day21.reachedInSteps(6)).isEqualTo(16);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day21/input.txt"))
            .toURI()));
    final Day21 day21 = new Day21(lines);
    Assertions.assertThat(day21.reachedInSteps(64)).isEqualTo(3773);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day21/input.txt"))
            .toURI()));
    final Day21 day21 = new Day21(lines);
    Assertions.assertThat(day21.reachedInStepsInfinite(26501365L)).isEqualTo(625628021226274L);
  }
}