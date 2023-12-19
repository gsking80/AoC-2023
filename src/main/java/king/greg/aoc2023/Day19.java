package king.greg.aoc2023;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.lang3.tuple.Pair;

public class Day19 {

  private final Map<String, List<List<String>>> workflows;
  private final List<Map<String, Integer>> parts;

  Day19(final List<String> lines) {
    final Pattern pattern = Pattern.compile(
        "\\{(\\w)=(\\d+),(\\w)=(\\d+),(\\w)=(\\d+),(\\w)=(\\d+)}");
    this.workflows = new HashMap<>();
    this.parts = new ArrayList<>();
    boolean parseFlows = true;
    for (final var line : lines) {
      if (line.isBlank()) {
        parseFlows = false;
      } else if (parseFlows) {
        final Pair<String, List<List<String>>> workflow = parseWorkflow(line);
        workflows.put(workflow.getKey(), workflow.getValue());
      } else {
        final Map<String, Integer> part = new HashMap<>();
        final var matches = pattern.matcher(line);
        if (matches.matches()) {
          for (int i = 1; i < matches.groupCount(); i += 2) {
            part.put(matches.group(i), Integer.parseInt(matches.group(i + 1)));
          }
        }
        parts.add(part);
      }
    }
  }

  public int sumAcceptedParts() {
    int sumAcceptedParts = 0;
    for (final var part : parts) {
      if (acceptedPart("in", part)) {
        sumAcceptedParts += part.values().stream().reduce(0, Integer::sum);
      }
    }
    return sumAcceptedParts;
  }

  public long validCombinations() {
    final Map<String, Pair<Integer, Integer>> initialRanges = new HashMap<>();
    initialRanges.put("x", Pair.of(1, 4000));
    initialRanges.put("m", Pair.of(1, 4000));
    initialRanges.put("a", Pair.of(1, 4000));
    initialRanges.put("s", Pair.of(1, 4000));

    final List<Map<String, Pair<Integer, Integer>>> validRanges = validPartRanges("in",
        initialRanges);
    long totalCombinations = 0;
    for (final var range : validRanges) {
      long combinations = 1;
      for (final var rangePair : range.values()) {
        combinations *= (rangePair.getRight() - rangePair.getLeft()) + 1;
      }
      totalCombinations += combinations;
    }

    return totalCombinations;
  }

  private List<Map<String, Pair<Integer, Integer>>> validPartRanges(final String workflowName,
      final Map<String, Pair<Integer, Integer>> partRanges) {
    if ("A".equals(workflowName)) {
      return List.of(partRanges);
    }
    if ("R".equals(workflowName)) {
      return Collections.emptyList();
    }
    final var workflow = workflows.get(workflowName);
    final List<Map<String, Pair<Integer, Integer>>> validPartRanges = new ArrayList<>();
    for (final var step : workflow) {
      if (step.size() == 1) {
        validPartRanges.addAll(validPartRanges(step.get(0), partRanges));
        return validPartRanges;
      }
      final var category = step.get(0);
      final var comparison = step.get(1);
      final var testValue = Integer.valueOf(step.get(2));
      final var targetWorkflow = step.get(3);
      final Map<String, Pair<Integer, Integer>> testRanges = new HashMap<>();
      for (final var entry : partRanges.entrySet()) {
        if (entry.getKey().equals(category)) {
          final var range = entry.getValue();
          if (comparison.equals("<")) {
            if (range.getRight() < testValue) {
              testRanges.put(entry.getKey(), Pair.of(range.getLeft(), range.getRight()));
              entry.setValue(Pair.of(0, 0));
            } else if (range.getLeft() >= testValue) {
              break;
            } else {
              testRanges.put(entry.getKey(), Pair.of(range.getLeft(), testValue - 1));
              entry.setValue(Pair.of(testValue, range.getRight()));
            }
          } else {
            if (range.getLeft() > testValue) {
              testRanges.put(entry.getKey(), Pair.of(range.getLeft(), range.getRight()));
              entry.setValue(Pair.of(0, 0));
            } else if (range.getRight() <= testValue) {
              break;
            } else {
              testRanges.put(entry.getKey(), Pair.of(testValue + 1, range.getRight()));
              entry.setValue(Pair.of(range.getLeft(), testValue));
            }
          }
        } else {
          final var range = entry.getValue();
          testRanges.put(entry.getKey(), Pair.of(range.getLeft(), range.getRight()));
        }
      }
      validPartRanges.addAll(validPartRanges(targetWorkflow, testRanges));
    }
    return validPartRanges;
  }

  private boolean acceptedPart(final String workflowName, final Map<String, Integer> part) {
    if ("A".equals(workflowName)) {
      return true;
    }
    if ("R".equals(workflowName)) {
      return false;
    }
    final var workflow = workflows.get(workflowName);
    for (final var step : workflow) {
      if (step.size() == 1) {
        return acceptedPart(step.get(0), part);
      }
      final var categoryValue = part.get(step.get(0));
      final var comparison = step.get(1);
      final var testValue = Integer.valueOf(step.get(2));
      final var targetWorkflow = step.get(3);
      if (("<".equals(comparison) && categoryValue < testValue) || (">".equals(comparison)
          && categoryValue > testValue)) {
        return acceptedPart(targetWorkflow, part);
      }
    }
    return false;
  }

  private Pair<String, List<List<String>>> parseWorkflow(final String line) {
    final var workflowParts = line.split("[{},]");
    final var flowName = workflowParts[0];
    final List<List<String>> steps = new ArrayList<>();
    for (int i = 1; i < workflowParts.length; i++) {
      steps.add(workflowStep(workflowParts[i]));
    }
    return Pair.of(flowName, steps);
  }

  private List<String> workflowStep(final String stepString) {
    final var pieces = stepString.split(":");
    if (pieces.length == 1) {
      return List.of(pieces);
    }
    final List<String> step = new ArrayList<>();
    if (pieces[0].contains("<")) {
      final var testPieces = pieces[0].split("<");
      step.add(testPieces[0]);
      step.add("<");
      step.add(testPieces[1]);
    } else {
      final var testPieces = pieces[0].split(">");
      step.add(testPieces[0]);
      step.add(">");
      step.add(testPieces[1]);
    }
    step.add(pieces[1]);
    return step;
  }
}
