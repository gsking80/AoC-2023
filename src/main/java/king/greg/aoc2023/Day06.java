package king.greg.aoc2023;

import java.util.List;

public class Day06 {

  final String[] times;
  final String[] distances;

  Day06(final List<String> lines) {
    times = lines.get(0).split(" +");
    distances = lines.get(1).split(" +");
  }

  public long totalWaysToWin() {

    long totalWaysToWin = 1;
    for (var i = 1; i < times.length; i++) {
      totalWaysToWin *= waysToWin(Long.parseLong(times[i]), Long.parseLong(distances[i]));
    }
    return totalWaysToWin;
  }

  public long totalWaysToWinKerning() {
    StringBuilder time = new StringBuilder();
    StringBuilder distance = new StringBuilder();
    for (var i = 1; i < times.length; i++) {
      time.append(times[i]);
      distance.append(distances[i]);
    }
    return waysToWin(Long.parseLong(time.toString()), Long.parseLong(distance.toString()));
  }

  private long waysToWin(final long time, final long distance) {
    var x = Math.ceil((time - Math.sqrt((time * time) - (double) (4 * (distance + 1)))) / 2);
    return (long) (time + 1 - (x * 2));
  }
}
