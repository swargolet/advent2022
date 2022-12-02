package com.sw.advent.days;

import java.util.Arrays;
import java.util.Map;
import lombok.Getter;

public class Day2b implements Day {

  @Getter
  private final String day = "Day2";

  private static final Map<String, Integer> LINE_SCORES = Map.of(
      "B X", 1,
      "C X", 2,
      "A X", 3,
      "A Y", 4,
      "B Y", 5,
      "C Y", 6,
      "C Z", 7,
      "A Z", 8,
      "B Z", 9
  );

  @Override
  public void process(String contents) {
    String[] lines = contents.split("\n");

    int sum = Arrays.stream(lines)
        .mapToInt(LINE_SCORES::get)
        .sum();

    System.out.println(sum);
  }
}
