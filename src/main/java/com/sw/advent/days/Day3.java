package com.sw.advent.days;

import java.util.Arrays;

public class Day3 implements Day<Integer> {
  @Override
  public Integer part1(String contents) {
    String[] lines = contents.split("\n");

    return Arrays.stream(lines)
        .map(String::toCharArray)
        .map(this::getDupe)
        .mapToInt(c -> Character.isUpperCase(c) ? c - 38 : c - 96)
        .sum();
  }

  // using only streams
//  private int getDupe(char[] chars) {
//    int half = chars.length / 2;
//    return IntStream.range(0, half)
//        .map(i -> chars[i])
//        .filter(c1 -> IntStream.range(half, chars.length)
//            .map(i -> chars[i])
//            .anyMatch(c2 -> c1 == c2))
//        .findFirst()
//        .orElseThrow();
//  }

  private int getDupe(char[] chars) {
    int half = chars.length / 2;
    for (int i = 0; i < half; i++) {
      for (int j = half; j < chars.length; j++) {
        if (chars[i] == chars[j]) {
          return chars[i];
        }
      }
    }
    throw new IllegalStateException("No matching char found");
  }


  @Override
  public Integer part2(String contents) {
    String[] lines = contents.split("\n");

    int sum = 0;
    for (int i = 0; i < lines.length; i = i + 3) {
      char badge = (char) getBadge(lines[i], lines[i + 1], lines[i + 2]);
      sum += Character.isUpperCase(badge) ? badge - 38 : badge - 96;
    }

    return sum;
  }

  private int getBadge(String l1, String l2, String l3) {
    return l1.chars()
        .filter(c -> l2.indexOf(c) != -1 && l3.indexOf(c) != -1)
        .findFirst()
        .orElseThrow();
  }
}
