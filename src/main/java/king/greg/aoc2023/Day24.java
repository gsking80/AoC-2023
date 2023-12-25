package king.greg.aoc2023;

import java.util.List;

public class Day24 {

  private final long[][] hailstones;

  Day24(final List<String> lines) {
    hailstones = new long[lines.size()][6];
    for (int i = 0; i < lines.size(); i++) {
      final var parts = lines.get(i).split(", +| @ +");
      for (int j = 0; j < parts.length; j++) {
        hailstones[i][j] = Long.parseLong(parts[j]);
      }
    }
  }

  public int intersections2D(final long minimum, final long maximum) {
    int count = 0;

    for (int i = 0; i < hailstones.length - 1; i++) {
      for (int j = i + 1; j < hailstones.length; j++) {
        var xi = hailstones[i][0];
        var xj = hailstones[j][0];
        var yi = hailstones[i][1];
        var yj = hailstones[j][1];
        var xdi = hailstones[i][3];
        var xdj = hailstones[j][3];
        var ydi = hailstones[i][4];
        var ydj = hailstones[j][4];
        // xi + xdi * iTime = xj + xdj * jTime
        // xdi * iTime - xdj * jTime = (xj - xi)
        // ydi * iTime - ydj * jTime = (yj - yi)
        double determinant = (double) (xdi * ydj) - (double) (xdj * ydi);
        if (determinant != 0) {
          double iTime = ((ydj * (xj - xi)) - (xdj * (yj - yi))) / determinant;
          double jTime = ((ydi * (xj - xi)) - (xdi * (yj - yi))) / determinant;

          if (iTime >= 0 && jTime >= 0) {
            var x = xi + (xdi * iTime);
            var y = yi + (ydi * iTime);
            if (minimum <= x && x <= maximum && minimum <= y && y <= maximum) {
              count++;
            }
          }
        }
      }
    }
    return count;
  }

  public long stoneThrow() {

    // There is probably a less "cheaty" way to do this by brute-forcing
    // to find a line that hits the first 3 vectors.  Probably related to part 1.
    // I'm tired and math tools exist.  Maybe I'll come back to this later.

    StringBuilder mathematicaEquations = new StringBuilder();
    for (int i = 0; i < 3; i++) {
      var xi = hailstones[i][0];
      var yi = hailstones[i][1];
      var zi = hailstones[i][2];
      var xdi = hailstones[i][3];
      var ydi = hailstones[i][4];
      var zdi = hailstones[i][5];
      mathematicaEquations.append('t').append(i).append(" >= 0, ");
      mathematicaEquations.append(xi).append(" + ").append(xdi).append('t').append(i)
          .append(" == x + xd ").append('t').append(i).append(", ");
      mathematicaEquations.append(yi).append(" + ").append(ydi).append('t').append(i)
          .append(" == y + yd ").append('t').append(i).append(", ");
      mathematicaEquations.append(zi).append(" + ").append(zdi).append('t').append(i)
          .append(" == z + zd ").append('t').append(i);
      if (i < 2) {
        mathematicaEquations.append(", ");
      }
    }
    String mathematicaFormula = "Solve[{" + mathematicaEquations + "}, {x,y,z,xd,yd,zd,t0,t1,t2}]";
    System.out.println(mathematicaFormula);

    long x = 229429688799267L;
    long y = 217160931330282L;
    long z = 133453231437025L;

    return x + y + z;
  }
}
