package com.sw.advent.days;

import java.util.Arrays;
import java.util.Comparator;

public class Day1 implements Day<Integer> {
  @Override
  public Integer part1(String contents) {
    return Arrays.stream(contents.split("\n\n"))
        .mapToInt(col -> Arrays.stream(col.split("\n"))
            .mapToInt(Integer::parseInt)
            .sum())
        .max()
        .orElseThrow();
  }

  @Override
  public Integer part2(String contents) {
    return Arrays.stream(contents.split("\n\n"))
        .map(col -> Arrays.stream(col.split("\n"))
            .mapToInt(Integer::parseInt)
            .sum())
        .sorted(Comparator.reverseOrder())
        .limit(3)
        .mapToInt(Integer::intValue)
        .sum();
  }
}
