package king.greg.aoc2023;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.tuple.Triple;

public class Day14 {

  private static final List<Point> directions = Arrays.asList(
      new Point(0, -1),
      new Point(-1, 0),
      new Point(0, 1),
      new Point(1, 0));
  private final Set<Point> cubeRocks;
  private Set<Point> roundedRocks;
  private int xMax;
  private int yMax;

  Day14(final List<String> lines) {
    this.cubeRocks = new HashSet<>();
    this.roundedRocks = new HashSet<>();
    for (int y = 0; y < lines.size(); y++) {
      for (int x = 0; x < lines.get(y).length(); x++) {
        if (lines.get(y).charAt(x) == '#') {
          cubeRocks.add(new Point(x, y));
        } else if (lines.get(y).charAt(x) == 'O') {
          roundedRocks.add(new Point(x, y));
        }
        xMax = x;
      }
      yMax = y;
    }
  }

  public int totalLoad() {
    tilt(directions.get(0));
    return calculateLoad();
  }

  public int totalLoadAfterCycles(final int numSpins) {
    final var cycleInfo = calculateCycleInfo();
    final var periodLength = cycleInfo.getMiddle();
    final var offset = cycleInfo.getLeft();
    final var remainder = (numSpins - offset) % periodLength;
    return cycleInfo.getRight().get(offset + remainder - 1);
  }

  private Triple<Integer, Integer, List<Integer>> calculateCycleInfo() {
    final List<Integer> loads = new ArrayList<>();
    int detectedLength = -1;
    int detectedStart = -1;
    int detectedCount = 0;
    int i = 0;
    while (true) {
      for (final var direction : directions) {
        tilt(direction);
      }
      var currentLoad = calculateLoad();
      var previousIndex = loads.lastIndexOf(currentLoad);
      loads.add(currentLoad);
      if (detectedCount > 0 && loads.get(i - detectedLength).equals(currentLoad)) {
        detectedCount++;
      } else if (-1 == previousIndex) {
        detectedLength = -1;
        detectedStart = -1;
        detectedCount = 0;
      } else {
        int length = i - previousIndex;
        if (length != detectedLength) {
          detectedLength = length;
          detectedStart = previousIndex;
          detectedCount = 1;
        }
      }
      if (detectedCount >= 50) { // Arbitrary cutoff that works for sample and input
        return Triple.of(detectedStart, detectedLength, loads);
      }
      i++;
    }
  }

  private void tilt(final Point direction) {
    final Set<Point> movedRoundedRocks = new HashSet<>();
    final List<Point> rocksToMove;
    if (direction.equals(directions.get(0))) {
      rocksToMove = roundedRocks.stream().sorted(Comparator.comparingInt(p -> p.y)).toList();
    } else if (direction.equals(directions.get(1))) {
      rocksToMove = roundedRocks.stream().sorted(Comparator.comparingInt(p -> p.x)).toList();
    } else if (direction.equals(directions.get(2))) {
      rocksToMove = roundedRocks.stream().sorted(Comparator.comparingDouble(Point::getY).reversed())
          .toList();
    } else {
      rocksToMove = roundedRocks.stream().sorted(Comparator.comparingDouble(Point::getX).reversed())
          .toList();
    }
    for (final var rock : rocksToMove) {
      var movedRock = new Point(rock.x, rock.y);
      while (inBounds(movedRock) && !movedRoundedRocks.contains(movedRock) && !cubeRocks.contains(
          movedRock)) {
        movedRock.translate(direction.x, direction.y);
      }
      movedRock.translate(-1 * direction.x, -1 * direction.y);
      movedRoundedRocks.add(movedRock);
    }
    roundedRocks = movedRoundedRocks;
  }

  private int calculateLoad() {
    int load = 0;
    for (final var rock : roundedRocks) {
      load += (yMax + 1) - rock.y;
    }
    return load;
  }

  private boolean inBounds(final Point rock) {
    return -1 < rock.x && rock.x < xMax + 1 && -1 < rock.y && rock.y < yMax + 1;
  }
}
