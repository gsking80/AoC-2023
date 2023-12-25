package king.greg.aoc2023;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day25Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day25/sample1.txt"))
            .toURI()));
    final Day25 day25 = new Day25(lines);
    Assertions.assertThat(day25.splitScore()).isEqualTo(54);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day25/input.txt"))
            .toURI()));
    final Day25 day25 = new Day25(lines);
    Assertions.assertThat(day25.splitScore()).isEqualTo(514794);
  }
}