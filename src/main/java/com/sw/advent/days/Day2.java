package com.sw.advent.days;

import java.util.Arrays;
import java.util.Map;

public class Day2 implements Day<Integer> {

  @Override
  public Integer part1(String contents) {
    String[] lines = contents.split("\n");

    return Arrays.stream(lines)
        .map(String::toCharArray)
        .map(chars -> new int[]{chars[0] - 64, chars[2] - 23 - 64})
        .mapToInt(shapes -> calcGame(shapes[0], shapes[1]) + shapes[1])
        .sum();
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
  public Integer part2(String contents) {
    String[] lines = contents.split("\n");
    return Arrays.stream(lines)
        .map(String::toCharArray)
        .map(chars -> new int[]{chars[0] - 64, chars[2] - 23 - 64 - 2})
        .mapToInt(nums -> calcThrow(nums[0], nums[1]) + ((nums[1] + 1) * 3))
        .sum();
  }

  private int calcThrow(int p1, int res) {
    int temp = p1 + res;
    if (temp > 3) {
      return 1;
    } else if (temp < 1) {
      return 3;
    }
    return temp;
  }
}
