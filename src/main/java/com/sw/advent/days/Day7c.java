package com.sw.advent.days;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Day7c implements Day {

  @Override
  public void process(String contents) {
    String[] lines = contents.split("\n");

    Map<String, Long> sizes = new HashMap<>();
    Deque<String> curDir = new ArrayDeque<>();
    curDir.add("/");
    for (int i = 1; i < lines.length; i++) {
      String line = lines[i];
      String[] prts = line.split(" ");
      if (prts[0].equals("$")) {
        if (prts[1].equals("cd")) {
          if (prts[2].equals("..")) {
            curDir.removeLast();
          } else {
            curDir.add(prts[2]);
          }
        }
      } else if (!prts[0].equals("dir")) {
        String accum  = "";
        for (String y : curDir) {
          accum += y;
          sizes.put(accum, sizes.getOrDefault(accum, 0L) + Long.parseLong(prts[0]));
        }
      }
    }

    long sum = sizes.values().stream().filter(l -> l <= 100000).mapToLong(Long::longValue).sum();
    System.out.println(sum);
    System.out.println(1432936 == sum);

    // Part 2
    long neededSpace = 30000000 - (70000000 - sizes.get("/"));
    long dirToDelete = sizes.values().stream().filter(l -> l >= neededSpace).sorted().findFirst().orElseThrow();
    System.out.println(dirToDelete);
    System.out.println(272298 == dirToDelete);
  }
}
