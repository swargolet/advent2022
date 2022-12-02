package com.sw.advent.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;

public class Day1b implements Day {
  @Getter
  private final String day = "Day1b";

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
