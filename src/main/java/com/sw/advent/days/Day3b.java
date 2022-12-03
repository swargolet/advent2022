package com.sw.advent.days;

import lombok.Getter;

public class Day3b implements Day {

  @Getter
  private final String day = "Day3";

  @Override
  public void process(String contents) {
    String[] lines = contents.split("\n");

    int sum = 0;
    for (int i = 0; i < lines.length; i = i + 3) {
      char badge = (char) getBadge(lines[i], lines[i + 1], lines[i + 2]);
      sum += Character.isUpperCase(badge) ? badge - 38 : badge - 96;
    }

    System.out.println(sum);
  }

  private int getBadge(String l1, String l2, String l3) {
    return l1.chars()
        .filter(c -> l2.indexOf(c) != -1 && l3.indexOf(c) != -1)
        .findFirst()
        .orElseThrow();
  }
}
