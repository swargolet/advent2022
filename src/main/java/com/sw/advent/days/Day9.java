package com.sw.advent.days;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;

public class Day9 implements Day {

  @Override
  public void process(String contents) {
    String[] lines = contents.split("\n");
    part1(lines);
    part2(lines);
  }

  void part1(String[] lines) {
    System.out.println(handleRope(lines, 2));
  }

  private void part2(String[] lines) {
    System.out.println(handleRope(lines, 10));
  }

  private int handleRope(String[] lines, int length) {
    Point[] points = Stream.generate(Point::new).limit(length).toArray(Point[]::new);

    Set<Point> tailPoints = new HashSet<>();
    tailPoints.add(new Point());
    for (String line : lines) {
      char dir = line.charAt(0);
      int amt = Integer.parseInt(line.substring(2));
      for (int i = 0; i < amt; i++) {
        switch (dir) {
          case 'U' -> points[0].y++;
          case 'D' -> points[0].y--;
          case 'L' -> points[0].x--;
          case 'R' -> points[0].x++;
        }

        for (int p = 0; p < length - 1; p++) {
          movePoints(points[p], points[p + 1]);
        }
        tailPoints.add(new Point(points[length - 1]));
      }
    }
    return tailPoints.size();
  }

  void movePoints(Point p1, Point p2) {
    if (Math.abs(p1.x - p2.x) >  1 || (Math.abs(p1.y - p2.y) > 1)) {
      p2.x += Integer.signum(p1.x - p2.x);
      p2.y += Integer.signum(p1.y - p2.y);
    }
  }

  @Data
  @AllArgsConstructor
  static class Point {
    int x;
    int y;

    public Point() {
      this.x = 0;
      this.y = 0;
    }
    public Point(Point p) {
      this.x = p.x;
      this.y = p.y;
    }
  }
}
