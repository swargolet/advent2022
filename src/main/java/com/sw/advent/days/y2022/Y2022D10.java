package com.sw.advent.days.y2022;

import com.sw.advent.days.Day;

public class Y2022D10 implements Day<Integer> {

  int x, cycle, sum;
  @Override
  public Integer part1(String contents) {
    x = 1;
    cycle = 0;
    sum = 0;
    String[] lines = contents.split("\n");
    for (String line : lines) {
      String[] s = line.split(" ");
      doCycle();
      if ("addx".equals(s[0])) {
        doCycle();
        x += Integer.parseInt(s[1]);
      }
    }
    return sum;
  }

  StringBuilder sb = new StringBuilder();

  @Override
  public Integer part2(String contents) {
    x = 1;
    cycle = 0;
    sum = 0;
    String[] lines = contents.split("\n");
    for (String line : lines) {
      String[] s = line.split(" ");
      doLine();
      doCycle();
      if ("addx".equals(s[0])) {
        doLine();
        doCycle();
        x += Integer.parseInt(s[1]);
      }
    }
    System.out.println(sb);
    return -1;
  }

  void doCycle() {
    cycle++;
    if ((cycle + 20) % 40 == 0) {
      sum += (cycle * x);
    }
  }

  void doLine() {
    int pos = cycle % 40;
    if (pos == 0) {
      sb.append("\n");
    }
    if (pos >= x-1 && pos <= x + 1) {
      sb.append("#");
    } else {
      sb.append(".");
    }
  }
}
