package com.sw.advent.days;

import java.util.Arrays;

public class Day4 implements Day<Long> {

  @Override
  public Long part1(String contents) {
    String[] lines = contents.split("\n");

    return Arrays.stream(lines)
        .map(line -> line.split("[,-]"))
        .map(pair -> Arrays.stream(pair)
            .mapToInt(Integer::parseInt)
            .toArray())
        .filter(ary -> ary[0] >= ary[2] && ary[1] <= ary[3] || ary[0] <= ary[2] && ary[1] >= ary[3])
        .count();
  }

  @Override
  public Long part2(String contents) {
    String[] lines = contents.split("\n");

    return Arrays.stream(lines)
        .map(line -> line.split("[,-]"))
        .map(pair -> Arrays.stream(pair)
            .mapToInt(Integer::parseInt)
            .toArray())
        .filter(ary -> ary[0] <= ary[3] && ary[1] >= ary[2] || ary[0] >= ary[3] && ary[1] <= ary[2])
        .count();
  }
}
