package com.sw.advent.days;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Day5 implements Day {

  @Override
  public void process(String contents) {
    String[] stackInstructions = contents.split("\n\n");

    Map<Integer, Deque<Character>> stacks = new HashMap<>();
    String[] stackLines = stackInstructions[0].split("\n");
    for (int i = stackLines.length - 2; i >= 0; i--) {
      char[] chars = stackLines[i].toCharArray();
      for (int j = 1; j < chars.length; j = j + 4) {
        char s = chars[j];
        if (Character.isAlphabetic(s)) {
          stacks.computeIfAbsent((j + 3) / 4, k -> new LinkedList<>()).push(s);
        }
      }
    }

    for (String line : stackInstructions[1].split("\n")) {
      String[] instructions = line.split(" ");
      int numPackages = Integer.parseInt(instructions[1]);
      int stackToPop = Integer.parseInt(instructions[3]);
      int stackToPush = Integer.parseInt(instructions[5]);

      for (int i = 0; i < numPackages; i++) {
        stacks.get(stackToPush).push(stacks.get(stackToPop).pop());
      }
    }

    String res = stacks.values().stream()
        .map(Deque::pop)
        .map(Objects::toString)
        .collect(Collectors.joining());
    System.out.println(res);
  }
}
