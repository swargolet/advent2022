package com.sw.advent.days;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day5b implements Day {

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

      ArrayDeque<Character> tmp = IntStream.range(0, numPackages)
          .boxed()
          .map(i -> stacks.get(stackToPop).removeFirst())
          .collect(Collectors.toCollection(ArrayDeque::new));

      tmp.addAll(stacks.get(stackToPush));
      stacks.put(stackToPush, tmp);
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
        stacks.computeIfAbsent(stackNum, k -> new ArrayDeque<>()).push(s);
      }
      stackNum++;
    }
  }
}
