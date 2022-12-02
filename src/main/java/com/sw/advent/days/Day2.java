package com.sw.advent.days;

import java.util.Arrays;
import lombok.Getter;

public class Day2 implements Day {

  @Getter
  private final String day = "Day2";

  @Override
  public void process(String contents) {
    String[] lines = contents.split("\n");

    int sum = Arrays.stream(lines)
        .map(String::toCharArray)
        .map(chars -> new int[]{chars[0] - 64, chars[2] - 23 - 64})
        .mapToInt(shapes -> calcGame(shapes[0], shapes[1]) + shapes[1])
        .sum();

    System.out.println(sum);
  }

  private int calcGame(int p1, int p2) {
    int preScore = p2 - p1;
    return switch (preScore) {
      case 2 -> 0;
      case -2 -> 6;
      default -> (preScore + 1) * 3;
    };
  }

  // Just stupid... but it works...
//  private int calcGame(int p1, int p2) {
//    int x = p2 - p1;
//    return (int) Math.sin((int) (x * 1.5) * Math.PI / 2) * 3 + 3;
//  }
}
