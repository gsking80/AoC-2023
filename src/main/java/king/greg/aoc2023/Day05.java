package king.greg.aoc2023;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

public class Day05 {

  private final List<Long> seeds = new ArrayList<>();
  private final List<List<Triple<Long, Long, Long>>> maps = new ArrayList<>();

  Day05(final List<String> lines) {
    int section = -1;
    boolean sectionSwitch = false;
    for (final var line : lines) {
      if (sectionSwitch) {
        sectionSwitch = false;
        maps.add(new ArrayList<>());
        section++;
      } else if (line.isEmpty()) {
        sectionSwitch = true;
      } else if (section == -1) {
        parseSeeds(line);
      } else {
        maps.get(section).add(parseMap(line));
      }
    }
    for (final var map : maps) {
      map.sort((Comparator.comparingLong(Triple::getMiddle)));
    }
  }

  public long lowestLocation() {
    long lowestLocation = Long.MAX_VALUE;
    for (final var seed : seeds) {
      long value = seed;
      for (final var map : maps) {
        value = nextValue(map, value);
      }
      if (value < lowestLocation) {
        lowestLocation = value;
      }
    }
    return lowestLocation;
  }

  public long lowestLocationByPair() {
    List<ImmutablePair<Long, Long>> ranges = new ArrayList<>();
    for (var i = 0; i < seeds.size(); i += 2) {
      ranges.add(new ImmutablePair<>(seeds.get(i), seeds.get(i + 1)));
    }
    for (final var map : maps) {
      ranges = nextRanges(map, ranges);
    }
    return ranges.stream().mapToLong(Pair::getLeft).min().orElse(Long.MAX_VALUE);
  }

  private List<ImmutablePair<Long, Long>> nextRanges(final List<Triple<Long, Long, Long>> map,
      final List<ImmutablePair<Long, Long>> ranges) {
    final List<ImmutablePair<Long, Long>> nextRanges = new ArrayList<>();
    for (final var range : ranges) {
      long lower = range.getLeft();
      long count = range.getRight();
      for (final var section : map) {
        final var lowerBound = section.getMiddle();
        final var upperBound = section.getMiddle() + section.getRight() - 1;
        while ((count > 0) && (lower <= upperBound)) {
          if (lower < lowerBound) {
            if ((lower + count - 1) < lowerBound) {
              nextRanges.add(new ImmutablePair<>(lower, count));
              count = 0;
            } else {
              var tempCount = lowerBound - lower;
              nextRanges.add(new ImmutablePair<>(lower, tempCount));
              lower = lowerBound;
              count -= tempCount;
            }
          } else {
            if ((lower + count - 1) <= upperBound) {
              nextRanges.add(
                  new ImmutablePair<>((lower - section.getMiddle()) + section.getLeft(), count));
              count = 0;
            } else {
              var tempCount = upperBound + 1 - lower;
              nextRanges.add(new ImmutablePair<>((lower - section.getMiddle()) + section.getLeft(),
                  tempCount));
              lower = upperBound + 1;
              count -= tempCount;
            }
          }
        }
      }
      if (count > 0) {
        nextRanges.add(new ImmutablePair<>(lower, count));
      }
    }
    return nextRanges;
  }

  private long nextValue(final List<Triple<Long, Long, Long>> map, final Long value) {
    for (final var section : map) {
      if (value < section.getMiddle()) {
        return value;
      }
      if (section.getMiddle() <= value && value < section.getMiddle() + section.getRight()) {
        return (value - section.getMiddle()) + section.getLeft();
      }
    }
    return value;
  }

  private void parseSeeds(final String seedLine) {
    final var parts = seedLine.split(" ");
    for (var i = 1; i < parts.length; i++) {
      seeds.add(Long.parseLong(parts[i]));
    }
  }

  private Triple<Long, Long, Long> parseMap(final String mapLine) {
    final var parts = mapLine.split(" ");
    return new ImmutableTriple<>(Long.parseLong(parts[0]), Long.parseLong(parts[1]),
        Long.parseLong(parts[2]));
  }
}
