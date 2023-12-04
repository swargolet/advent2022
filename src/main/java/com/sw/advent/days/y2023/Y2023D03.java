package com.sw.advent.days.y2023;

import com.sw.advent.days.Day;

import java.util.*;
import java.util.stream.Stream;


public class Y2023D03 implements Day<Integer> {

  private static final int[][] ADJACENTS = {
      {-1, -1}, {-1, 0}, {-1, 1},
      {0, -1}, {0, 0}, {0, 1},
      {1, -1}, {1, 0}, {1, 1}
  };

  @Override
  public Integer part1(String contents) {

    String[] lines = contents.split("\n");
    Boolean[][] symbols = Stream.of(lines)
        .map(line -> line.chars()
            .mapToObj(c -> !(Character.isDigit(c) || c == '.'))
            .toArray(Boolean[]::new))
        .toArray(Boolean[][]::new);

    boolean[][] adjacentSymbol = adjacentSymbol(symbols);

    int sum = 0;
    for (int row = 0; row < lines.length; row++) {
      String line = lines[row];
      char[] charArray = line.toCharArray();
      int curNum = 0;
      boolean shouldCount = false;
      for (int col = 0; col < charArray.length; col++) {
        char c = charArray[col];
        if (Character.isDigit(c)) {
          curNum = curNum * 10 + c - '0';
          if (adjacentSymbol[row][col]) {
            shouldCount = true;
          }
        } else if (curNum != 0) {
          if (shouldCount) {
            sum += curNum;
          }
          curNum = 0;
          shouldCount = false;
        }
      }
      if (shouldCount) { // stupid edge case of number being at end of the line
        sum += curNum;
      }
    }
    return sum;
  }

  private boolean[][] adjacentSymbol(Boolean[][] symbols) {
    int maxRows = symbols.length;
    int maxCols = symbols[0].length;
    boolean[][] adjacent = new boolean[maxRows][maxCols];

    for (int row = 0; row < maxRows; row++) {
      Boolean[] symbolRow = symbols[row];
      for (int col = 0; col < symbolRow.length; col++) {
        Boolean isSymbol = symbolRow[col];
        if (isSymbol) {
          for (int aRow = Math.max(0, row - 1); aRow <= Math.min(maxRows - 1, row + 1); aRow++) {
            for (int aCol = Math.max(0, col - 1); aCol <= Math.min(maxCols - 1, col + 1); aCol++) {
              adjacent[aRow][aCol] = true;
            }
          }
        }
      }
    }
    return adjacent;
  }


  @Override
  public Integer part2(String contents) {
    char[][] chars = contents.lines()
        .map(String::toCharArray)
        .toArray(char[][]::new);

    int sum = 0;
    for (int row = 0; row < chars.length; row++) {
      for (int col = 0; col < chars[row].length; col++) {
        char c = chars[row][col];
        if (c == '*') {
          sum += lookAtAdjacent(chars, row, col);
        }
      }
    }

    return sum;
  }

  private int lookAtAdjacent(char[][] chars, int gearRow, int gearCol) {
    Set<Integer> partNums = new HashSet<>();

    for (int[] adjacent : ADJACENTS) {
      int rowOffset = adjacent[0];
      int colOffset = adjacent[1];
      int newRow = gearRow + rowOffset;
      int newCol = gearCol + colOffset;

      if (newRow >= chars.length) continue;
      if (Character.isDigit(chars[newRow][newCol])) {
        int partNumber = getPartNumber(chars, newRow, newCol);
        partNums.add(partNumber);
      }
    }

    if (partNums.size() != 2) {
      return 0;
    } else {
      return partNums.stream().mapToInt(it -> it).reduce(1, (x, y) -> x * y);
    }
  }

  private int getPartNumber(char[][] chars, int newRow, int newCol) {
    while (newCol > 0) {
      if (!Character.isDigit(chars[newRow][newCol - 1])) {
        break;
      }
      newCol--;
    }

    int partNum = chars[newRow][newCol] - '0';
    while (newCol < chars[0].length - 1) {
      if (!Character.isDigit(chars[newRow][newCol + 1])) {
        break;
      }
      char curChar = chars[newRow][++newCol];
      partNum = partNum * 10 + (curChar - '0');
    }

    return partNum;
  }
}
