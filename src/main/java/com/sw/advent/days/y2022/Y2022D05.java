package com.sw.advent.days.y2022;

import com.sw.advent.days.Day;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Y2022D05 implements Day<String> {

  @Override
  public String part1(String contents) {
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

    return stacks.values().stream()
        .map(Deque::pop)
        .map(Objects::toString)
        .collect(Collectors.joining());
  }

  @Override
  public String part2(String contents) {
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

    return stacks.values().stream()
        .map(Deque::pop)
        .map(Objects::toString)
        .collect(Collectors.joining());
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
