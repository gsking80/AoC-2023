package king.greg.aoc2023;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day22 {

  private final List<Brick> bricks;

  Day22(final List<String> lines) {
    bricks = new ArrayList<>();
    for (final var line : lines) {
      bricks.add(new Brick(line));
    }
  }

  public int safeToDisintegrate() {
    landBricks();
    final Set<Brick> safeToRemove = new HashSet<>(bricks);
    for (final var brick : bricks) {
      if (brick.supportingBricks.size() == 1) {
        safeToRemove.removeAll(brick.supportingBricks);
      }
    }
    return safeToRemove.size();
  }

  public int sumDisintegrationChains() {
    landBricks();
    int sumDisitegrationChains = 0;
    for (final var brick : bricks) {
      sumDisitegrationChains += brick.disintegrationChainSize();
    }
    return sumDisitegrationChains;
  }

  private void landBricks() {
    bricks.sort(Comparator.comparingInt(Brick::getzMin));
    final List<Brick> landedBricks = new ArrayList<>();
    for (final var brick : bricks) {
      landedBricks.sort(Comparator.comparingInt(Brick::getzMax).reversed());
      int supportingHeight = 0;
      for (final var landedBrick : landedBricks) {
        if (landedBrick.zMax < supportingHeight) {
          break;
        }
        if (brick.sharesCylinder(landedBrick)) {
          brick.addSupportingBrick(landedBrick);
          supportingHeight = landedBrick.getzMax();
        }
      }
      brick.landOn(supportingHeight);
      landedBricks.add(brick);
    }
  }

  static class Brick {

    private final int xMin;
    private final int xMax;
    private final int yMin;
    private final int yMax;
    private final Set<Brick> supportingBricks;
    private int zMin;
    private int zMax;
    private Set<Brick> jengaBricks;

    public Brick(final String brickCoordinates) {
      final var coords = brickCoordinates.split("[,~]");
      final var x1 = Integer.parseInt(coords[0]);
      final var x2 = Integer.parseInt(coords[3]);
      if (x1 < x2) {
        xMin = x1;
        xMax = x2;
      } else {
        xMin = x2;
        xMax = x1;
      }
      final var y1 = Integer.parseInt(coords[1]);
      final var y2 = Integer.parseInt(coords[4]);
      if (y1 < y2) {
        yMin = y1;
        yMax = y2;
      } else {
        yMin = y2;
        yMax = y1;
      }
      final var z1 = Integer.parseInt(coords[2]);
      final var z2 = Integer.parseInt(coords[5]);
      if (z1 < z2) {
        zMin = z1;
        zMax = z2;
      } else {
        zMin = z2;
        zMax = z1;
      }
      supportingBricks = new HashSet<>();
    }

    public boolean sharesCylinder(final Brick otherBrick) {
      return (this.xMin <= otherBrick.xMax) &&
          (this.xMax >= otherBrick.xMin) &&
          (this.yMin <= otherBrick.yMax) &&
          (this.yMax >= otherBrick.yMin);
    }

    public int getzMin() {
      return zMin;
    }

    public int getzMax() {
      return zMax;
    }

    public void addSupportingBrick(final Brick otherBrick) {
      supportingBricks.add(otherBrick);
    }

    public void landOn(final int supportingHeight) {
      final var difference = zMin - (supportingHeight + 1);
      zMin -= difference;
      zMax -= difference;
    }

    public int disintegrationChainSize() {
      return getJengaBricks().size();
    }

    public Set<Brick> getJengaBricks() {
      if (null == jengaBricks) {
        jengaBricks = new HashSet<>();
        boolean firstBrick = true;
        for (final var supportingBrick : supportingBricks) {
          if (firstBrick) {
            jengaBricks.addAll(supportingBrick.getJengaBricks());
            firstBrick = false;
          } else {
            jengaBricks.retainAll(supportingBrick.getJengaBricks());
          }
        }
        if (supportingBricks.size() == 1) {
          jengaBricks.addAll(supportingBricks);
        }
      }
      return jengaBricks;
    }
  }
}
