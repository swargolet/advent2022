package com.sw.advent.days.y2022;

import com.sw.advent.days.Day;

import java.util.Arrays;

public class Y2022D12 implements Day<Integer> {

  public Integer part1(String contents) {

    String[] lines = contents.split("\n");
    char[][] chars = Arrays.stream(lines).map(String::toCharArray).toArray(char[][]::new);

    int lineLength = lines[0].length();
    int sInd = contents.indexOf('S');
    int sRow = sInd / lineLength;
    int sCol = (sInd - sRow) % lineLength;
    int eInd = contents.indexOf('E');
    int eRow = eInd / lineLength;
    int eCol = (eInd - eRow) % lineLength;

    char curChar = 'z';
    for (int i = 0; i < 10; i++) {
      System.out.println(curChar);
      char up = chars[eRow][eCol - 1]; // up
      if (curChar - up <= 1) {
        curChar = up;
        continue;
      }
      char down = chars[eRow][eCol + 1]; // down
      if (curChar - down <= 1) {
        curChar = down;
        continue;
      }
      char left = chars[eRow - 1][eCol]; // left
      if (curChar - left <= 1) {
        curChar = left;
        continue;
      }
      char right = chars[eRow + 1][eCol]; // right
      if (curChar - right <= 1) {
        curChar = right;
        continue;
      }
      System.out.println("No place to go");
      break;
    }

    return null;
  }

  public Integer part2(String contents) {
    return null;
  }
}
