package com.sw.advent.days;

public class Day6b implements Day {

  @Override
  public void process(String contents) {
    char[] chars = contents.toCharArray();

    for (int i = 0; i < chars.length - 14; i++) {
      int[] distinct = contents.substring(i, i + 14).chars().distinct().toArray();
      if (distinct.length == 14) {
        System.out.println(i + 14);
        break;
      }
    }
  }
}
