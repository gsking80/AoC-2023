package king.greg.aoc2023;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import org.apache.commons.lang3.tuple.Triple;

public class Day20 {

  public static final String BROADCASTER = "broadcaster";
  public static final String BUTTON = "button";
  private final Map<String, Module> modules;

  Day20(final List<String> lines) {
    modules = new HashMap<>();
    for (final var line : lines) {
      final var parts = line.split(" -> |, ");
      final String moduleName = BROADCASTER.equals(parts[0]) ? parts[0] : parts[0].substring(1);
      final Module module = new Module(moduleName, parts[0].charAt(0),
          Arrays.copyOfRange(parts, 1, parts.length));
      modules.put(moduleName, module);
    }
    for (final var module : modules.values()) {
      module.initializeConjunctions();
    }
  }

  public long pulseMultiplier(final long buttonPushes) {
    long lowCount = 0;
    long highCount = 0;
    for (int i = 0; i < buttonPushes; i++) {
      final Queue<Triple<String, String, Boolean>> pulses = new ArrayDeque<>();
      pulses.add(Triple.of(BROADCASTER, BUTTON, false));
      while (!pulses.isEmpty()) {
        final var pulse = pulses.remove();
        if (Boolean.TRUE.equals(pulse.getRight())) {
          highCount++;
        } else {
          lowCount++;
        }
        final var module = modules.get(pulse.getLeft());
        if (null != module) {
          pulses.addAll(module.pulse(pulse.getMiddle(), pulse.getRight()));
        }
      }
    }
    return lowCount * highCount;
  }

  public long pressesToHigh(final String testModule) {
    long buttonPushes = 0;
    final Set<String> conjunctions = modules.get(testModule).cablesIn.keySet();
    final Map<String, Long> importantCycles = new HashMap<>();

    while (conjunctions.size() != importantCycles.size()) {
      final Queue<Triple<String, String, Boolean>> pulses = new ArrayDeque<>();
      pulses.add(Triple.of(BROADCASTER, BUTTON, false));
      buttonPushes++;
      while (!pulses.isEmpty()) {
        final var pulse = pulses.remove();
        if (conjunctions.contains(pulse.getLeft()) && Boolean.FALSE.equals(pulse.getRight())) {
          importantCycles.putIfAbsent(pulse.getLeft(), buttonPushes);
        }
        final var module = modules.get(pulse.getLeft());
        if (null != module) {
          pulses.addAll(module.pulse(pulse.getMiddle(), pulse.getRight()));
        }
      }
    }

    long presses = 1;
    for (final var cycle : importantCycles.values()) {
      presses *= cycle;
    }
    return presses;
  }

  class Module {

    private final String name;
    private final char type;
    private final String[] cablesOut;
    private final Map<String, Boolean> cablesIn;
    private boolean on;

    public Module(final String name, final char type, final String[] cablesOut) {
      this.name = name;
      this.type = type;
      this.cablesOut = cablesOut;
      cablesIn = new HashMap<>();
    }

    public List<Triple<String, String, Boolean>> pulse(final String sourceCable,
        final boolean high) {
      final List<Triple<String, String, Boolean>> pulsesOut = new ArrayList<>();
      switch (type) {
        case 'b' -> {
          for (final var cable : cablesOut) {
            pulsesOut.add(Triple.of(cable, name, high));
          }
        }
        case '%' -> {
          if (!high) {
            on = !on;
            for (final var cable : cablesOut) {
              pulsesOut.add(Triple.of(cable, name, on));
            }
          }
        }
        case '&' -> {
          cablesIn.put(sourceCable, high);
          boolean inputHigh = true;
          for (final var input : cablesIn.values()) {
            inputHigh = inputHigh && input;
          }
          on = !inputHigh;
          for (final var cable : cablesOut) {
            pulsesOut.add(Triple.of(cable, name, on));
          }
        }
        default -> throw new IllegalStateException(type + " not supported");
      }
      return pulsesOut;
    }

    public void initializeConjunctions() {
      for (final var cable : cablesOut) {
        final var module = modules.get(cable);
        if (null != module) {
          module.addInput(name);
        }
      }
    }

    public void addInput(final String inputCable) {
      if ('&' == type) {
        cablesIn.put(inputCable, false);
      }
    }
  }
}
