package com.sw.advent.days;

import java.util.Arrays;
import java.util.stream.Collector;
import lombok.AllArgsConstructor;
import lombok.Data;

public class Day10b implements Day<Integer> {

  @Override
  public Integer part1(String contents) {
    return Arrays.stream(contents.split("\n"))
        .map(line -> line.startsWith("addx") ? new String[]{"", line} : new String[]{line})
        .flatMap(Arrays::stream)
        .map(line -> line.split(" "))
        .collect(Collector.of(
            () -> new int[]{1, 0, 0}, //x, cycle, sum
            (accm, s) -> {
              int cyc = accm[1] + 1;
              accm[1] = cyc;
              accm[2] = ((cyc + 20) % 40 == 0) ? accm[2] + (cyc * accm[0]) : accm[2];
              accm[0] = "addx".equals(s[0]) ? accm[0] + Integer.parseInt(s[1]) : accm[0];
            },
            (a, b) -> a,
            a -> a[2]
        ));
  }

  @Override
  public Integer part2(String contents) {
    StringBuilder sb = Arrays.stream(contents.split("\n"))
        .map(line -> line.startsWith("addx") ? new String[]{"", line} : new String[]{line})
        .flatMap(Arrays::stream)
        .map(line -> line.split(" "))
        .collect(Collector.of(
            () -> new Cycle(1, 0, 0, new StringBuilder()),
            (cycle, s) -> {
              int cyc = cycle.getCycle() + 1;
              cycle.setCycle(cyc);
              cycle.setSum(((cyc + 20) % 40 == 0) ? cycle.getSum() + (cyc * cycle.getX()) : cycle.getSum());
              cycle.setX("addx".equals(s[0]) ? cycle.getX() + Integer.parseInt(s[1]) : cycle.getX());

              int pos = cyc % 40;
              if (pos == 0) {
                cycle.getSb().append("\n");
              }
              cycle.getSb().append((pos >= cycle.getX() - 1 && pos <= cycle.getX() + 1) ? '#' : '.');
            },
            (a, b) -> a,
            Cycle::getSb
        ));

    System.out.println(sb);

    return -1;
  }


  @Data
  @AllArgsConstructor
  class Cycle {
    int x;
    int cycle;
    int sum;
    StringBuilder sb;
  }
}
