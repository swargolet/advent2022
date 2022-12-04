package com.sw.advent.days;

import java.util.Arrays;
import java.util.Comparator;

public class Day1b implements Day {

  @Override
  public void process(String contents) {
    int sum = Arrays.stream(contents.split("\n\n"))
        .map(col -> Arrays.stream(col.split("\n"))
            .mapToInt(Integer::parseInt)
            .sum())
        .sorted(Comparator.reverseOrder())
        .limit(3)
        .mapToInt(Integer::intValue)
        .sum();

    System.out.println(sum);
  }
}
