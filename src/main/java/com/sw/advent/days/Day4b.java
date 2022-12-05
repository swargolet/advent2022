package com.sw.advent.days;

import java.util.Arrays;

public class Day4b implements Day {

  @Override
  public void process(String contents) {
    String[] lines = contents.split("\n");

    long pairs = Arrays.stream(lines)
        .map(line -> line.split("[,-]"))
        .map(pair -> Arrays.stream(pair)
            .mapToInt(Integer::parseInt)
            .toArray())
        .filter(ary -> ary[0] <= ary[3] && ary[1] >= ary[2] || ary[0] >= ary[3] && ary[1] <= ary[2])
        .count();

    System.out.println(pairs);
  }
}
