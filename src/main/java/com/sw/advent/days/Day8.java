package com.sw.advent.days;

public class Day8 implements Day {

  private static final int gridSize = 99;

  @Override
  public void process(String contents) {
    String[] lines = contents.split("\n");

    int[][] trees = new int[gridSize][gridSize];
    for (int i = 0; i < gridSize; i++) {
      String line = lines[i];
      for (int j = 0; j < gridSize; j++) {
        trees[i][j] = line.charAt(j) - 48;
      }
    }

    part1(trees);
    part2(trees);
  }

  void part1(int[][] trees) {
    int outerEdge = gridSize * 4;
    int count = outerEdge;
    for (int i = 1; i < gridSize - 1; i++) {
      for (int j = 1; j < gridSize - 1; j++) {
        if (isTreeVisible(trees, i, j)) {
          count++;
        }
      }
    }

    System.out.println(count);
  }

  private boolean isTreeVisible(int[][] trees, int curRow, int curCol) {
    // look through row
    int curTree = trees[curRow][curCol];
    for (int k = 0; k < curCol; k++) { // left side
      if (curTree > trees[curRow][k]) {
        if (k == curCol - 1) {
          return true;
        }
      } else {
        break;
      }
    }
    for (int k = curCol + 1; k < gridSize; k++) { // right side
      if (curTree > trees[curRow][k]) {
        if (k == gridSize - 1) {
          return true;
        }
      } else {
        break;
      }
    }

    // col
    for (int k = 0; k < curRow; k++) { // top side
      if (curTree > trees[k][curCol]) {
        if (k == curRow - 1) {
          return true;
        }
      } else {
        break;
      }
    }

    for (int k = curRow + 1; k < gridSize; k++) { // top side
      if (curTree > trees[k][curCol]) {
        if (k == gridSize - 1) {
          return true;
        }
      } else {
        break;
      }
    }

    return false;
  }

  void part2(int[][] trees) {
    long max = 0;
    for (int curRow = 1; curRow < gridSize - 1; curRow++) {
      for (int curCol = 1; curCol < gridSize - 1; curCol++) {
        int curTree = trees[curRow][curCol];

        // look through row
        int tmpScore = 1;
        for (int k = curCol - 1; k >= 0; k--) { // left side
          if (curTree > trees[curRow][k] && k > 0) {
            tmpScore++;
          } else {
            break;
          }
        }
        int scenicScore = tmpScore;

        tmpScore = 1;
        for (int k = curCol + 1; k < gridSize; k++) { // right side
          if (curTree > trees[curRow][k] && k < gridSize - 1) {
            tmpScore++;
          } else {
            break;
          }
        }
        scenicScore *= tmpScore;

        // col
        tmpScore = 1;
        for (int k = curRow - 1; k >= 0; k--) { // top side
          if (curTree > trees[k][curCol] && k > 0) {
            tmpScore++;
          } else {
            break;
          }
        }
        scenicScore *= tmpScore;

        tmpScore = 1;
        for (int k = curRow + 1; k < gridSize; k++) { // bottom side
          if (curTree > trees[k][curCol] && k < gridSize - 1) {
            tmpScore++;
          } else {
            break;
          }
        }
        scenicScore *= tmpScore;

        if (scenicScore > max) {
          max = scenicScore;
        }
      }
    }
    System.out.println(max);
  }
}
