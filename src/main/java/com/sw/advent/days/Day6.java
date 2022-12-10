package com.sw.advent.days;

public class Day6 implements Day<Integer> {
  @Override
  public Integer part1(String contents) {
    char[] chars = contents.toCharArray();

    for (int i = 0; i < chars.length - 4; i++) {
      int[] distinct = contents.substring(i, i + 4).chars().distinct().toArray();
      if (distinct.length == 4) {
        return i + 4;
      }
    }
    return -1;
  }

  @Override
  public Integer part2(String contents) {
    char[] chars = contents.toCharArray();

    for (int i = 0; i < chars.length - 14; i++) {
      int[] distinct = contents.substring(i, i + 14).chars().distinct().toArray();
      if (distinct.length == 14) {
        return i + 14;
      }
    }
    return -1;
  }
}
