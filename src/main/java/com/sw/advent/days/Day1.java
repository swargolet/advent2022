package com.sw.advent.days;

import java.util.Arrays;
import java.util.stream.Collector;

public class Day1 implements Day {
  @Override
  public void process(String contents) {
    System.out.println(process1(contents));
    System.out.println(process2(contents));
    System.out.println(process3(contents));
  }

  public int process3(String contents) {
    return Arrays.stream(contents.split("\n\n"))
        .mapToInt(col -> Arrays.stream(col.split("\n"))
            .mapToInt(Integer::parseInt)
            .sum())
        .max()
        .orElseThrow();
  }

  public int process2(String contents) {
    String[] lines = contents.split("\n");
    return Arrays.stream(lines)
        .collect(Collector.of(
            () -> new int[2],
            (arr, line) -> {
              if ("".equals(line)) {
                arr[0] = Math.max(arr[0], arr[1]);
                arr[1] = 0;
              } else {
                arr[1] += Integer.parseInt(line);
              }
            },
            (a, b) -> a,
            a -> a[0]
        ));
  }

  public int process1(String contents) {
    String[] lines = contents.split("\n");
    int max = 0;
    int sum = 0;
    for (String line : lines) {
      if ("".equals(line)) {
        if (sum > max) {
          max = sum;
        }
        sum = 0;
      } else {
        sum += Integer.parseInt(line);
      }
    }
    return max;
  }
}
