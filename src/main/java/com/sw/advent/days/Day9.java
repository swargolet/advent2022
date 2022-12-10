package com.sw.advent.days;

import java.util.HashSet;
import java.util.Set;

public class Day9 implements Day {

  @Override
  public void process(String contents) {
    String[] lines = contents.split("\n");
    part1(lines);
//    part2(contents);
  }

  void part1(String[] lines) {
    int hx = 0;
    int hy = 0;
    int tx = 0;
    int ty = 0;
    Set<Point> tailPoints = new HashSet<>();

    tailPoints.add(new Point(tx, ty));
    for (String line : lines) {
      char dir = line.charAt(0);
      int amt = Integer.parseInt(line.substring(2));
      for (int i = 0; i < amt; i++) {
        if (dir == 'U') {
          hy++;
        } else if (dir == 'D') {
          hy--;
        } else if (dir == 'L') {
          hx--;
        } else if (dir == 'R') {
          hx++;
        }
        if (Math.abs(hx - tx) > 1) { // too far away on row
          tx += (hx - tx) / 2;
          if (hy != ty) { //move diagonally
            ty = hy;
          }
          tailPoints.add(new Point(tx, ty));
        } else if (Math.abs(hy - ty) > 1) { // too far away on col
          ty += (hy - ty) / 2;
          if (hx != tx) { //move diagonally
            tx = hx;
          }
          tailPoints.add(new Point(tx, ty));
        }
      }
    }
    System.out.println(tailPoints.size());
  }


  record Point(int x, int y) {

  }
}
