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
      parseStack(stacks, stackLines[i]);
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

  private void parseStack(Map<Integer, Deque<Character>> stacks, String line) {
    Integer stackNum = 1;
    char[] chars = line.toCharArray();
    for (int i = 1; i < chars.length; i = i + 4) {
      char s = chars[i];
      if (s != ' ' && Character.isAlphabetic(s)) {
        stacks.computeIfAbsent(stackNum, k -> new LinkedList<>()).push(s);
      }
      stackNum++;
    }
  }
}
