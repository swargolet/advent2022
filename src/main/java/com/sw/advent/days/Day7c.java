package com.sw.advent.days;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7c implements Day<Long> {
  Pattern dirP = Pattern.compile("\\$ cd (\\w+)");
  Pattern fileP = Pattern.compile("(\\d+) \\w+");

  @Override
  public void process(String contents) {
    String[] lines = contents.split("\n");
    Matcher m;
    Map<String, Long> sizes = new HashMap<>();
    Deque<String> curDir = new ArrayDeque<>();
    curDir.add("/");
    for (int i = 1; i < lines.length; i++) {
      String line = lines[i];
      if (line.matches("\\$ cd ..")) {
        curDir.removeLast();
      } else if ((m = dirP.matcher(line)).matches()) {
        curDir.add(m.group(1));
      } else if ((m = fileP.matcher(line)).matches()) {
        String accum  = "";
        for (String y : curDir) {
          accum += y;
          sizes.put(accum, sizes.getOrDefault(accum, 0L) + Long.parseLong(m.group(1)));
        }
      }
//      if (prts[0].equals("$")) {
//        if (prts[1].equals("cd")) {
//          if (prts[2].equals("..")) {
//            curDir.removeLast();
//          } else {
//            curDir.add(prts[2]);
//          }
//        }
//      } else if (!prts[0].equals("dir")) {
//        String accum  = "";
//        for (String y : curDir) {
//          accum += y;
//          sizes.put(accum, sizes.getOrDefault(accum, 0L) + Long.parseLong(prts[0]));
//        }
//      }
    }
    System.out.println(sizes);

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
