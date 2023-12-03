package com.sw.advent.days.y2022;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import com.sw.advent.days.Day;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Y2022D09 implements Day<Integer> {

  @Override
  public Integer part1(String contents) {
    return handleRope(contents, 2);
  }

  @Override
  public Integer part2(String contents) {
    return handleRope(contents, 10);
  }

  private int handleRope(String contents, int length) {
    String[] lines = contents.split("\n");
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
          Point p1 = points[p];
          Point p2 = points[p + 1];
          if (Math.abs(p1.x - p2.x) >  1 || (Math.abs(p1.y - p2.y) > 1)) {
            p2.x += Integer.signum(p1.x - p2.x);
            p2.y += Integer.signum(p1.y - p2.y);
          }
        }
        tailPoints.add(new Point(points[length - 1]));
      }
    }
    return tailPoints.size();
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  static class Point {
    int x;
    int y;

    public Point(Point p) {
      this.x = p.x;
      this.y = p.y;
    }
  }
}
