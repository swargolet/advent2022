package com.sw.advent.days.y2023;

import com.sw.advent.days.Day;

import java.util.Map;


public class Y2023D01 implements Day<Integer> {
  private static final Map<String, String> NUM_MAP = Map.of(
      "zero", "ze0ro",
      "one", "on1e",
      "two", "tw2o",
      "three", "thr3ee",
      "four", "fo4ur",
      "five", "fiv5e",
      "six", "si6x",
      "seven", "sev7en",
      "eight", "eig8ht",
      "nine", "ni9ne");

  @Override
  public Integer part1(String contents) {
    return contents.lines()
        .map(String::chars)
        .map(chars -> chars.filter(Character::isDigit).map(c -> c - '0').toArray())
        .mapToInt(ca -> ca[0] * 10 + ca[ca.length - 1])
        .sum();
  }

  @Override
  public Integer part2(String contents) {
    return contents.lines()
        .map(line -> {
          for (Map.Entry<String, String> entry : NUM_MAP.entrySet()) {
            line = line.replaceAll(entry.getKey(), entry.getValue());
          }
          return line;
        })
        .map(String::chars)
        .map(chars -> chars.filter(Character::isDigit).map(c -> c - '0').toArray())
        .mapToInt(ca -> ca[0] * 10 + ca[ca.length - 1])
        .sum();
  }
}
