package com.sw.advent.days.y2022;

import com.sw.advent.days.Day;

import java.util.Arrays;

public class Y2022D04 implements Day<Long> {

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
