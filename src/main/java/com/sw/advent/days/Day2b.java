package com.sw.advent.days;

import java.util.Arrays;
import java.util.Map;
import lombok.Getter;

public class Day2b implements Day {

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
  @Getter
  private final String day = "Day2";

  @Override
  public void process(String contents) {
    process1(contents);
    process2(contents);
  }

  public void process1(String contents) {
    String[] lines = contents.split("\n");

    int sum = Arrays.stream(lines)
        .mapToInt(LINE_SCORES::get)
        .sum();

    System.out.println(sum);
  }

  public void process2(String contents) {
    String[] lines = contents.split("\n");

    int sum = Arrays.stream(lines)
        .map(String::toCharArray)
        .map(chars -> new int[]{chars[0] - 64, chars[2] - 23 - 64 - 2})
        .mapToInt(nums -> calcThrow(nums[0], nums[1]) + ((nums[1] + 1) * 3))
        .sum();

    System.out.println(sum);
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
