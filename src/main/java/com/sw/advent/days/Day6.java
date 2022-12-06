package com.sw.advent.days;

public class Day6 implements Day {

  @Override
  public void process(String contents) {
    char[] chars = contents.toCharArray();

    for (int i = 0; i < chars.length - 4; i++) {
      int[] distinct = contents.substring(i, i + 4).chars().distinct().toArray();
      if (distinct.length == 4) {
        System.out.println(i + 4);
        return;
      }
    }
  }
}
