package com.sw.advent.days;

import java.util.Arrays;
import lombok.Getter;

public class Day3 implements Day {

  @Getter
  private final String day = "Day3";

  @Override
  public void process(String contents) {
    String[] lines = contents.split("\n");

    int sum = Arrays.stream(lines)
        .map(String::toCharArray)
        .map(this::getDupe)
        .mapToInt(c -> Character.isUpperCase(c) ? c - 38 : c - 96)
        .sum();

    System.out.println(sum);
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
}
