package com.sw.advent.days;

public class Day8 implements Day<Integer> {

  private static final int GRID_SIZE = 99;

  @Override
  public Integer part1(String contents) {
    String[] lines = contents.split("\n");
    int[][] trees = parseTrees(lines);
    int outerEdge = GRID_SIZE * 4;
    int count = outerEdge;
    for (int i = 1; i < GRID_SIZE - 1; i++) {
      for (int j = 1; j < GRID_SIZE - 1; j++) {
        if (isTreeVisible(trees, i, j)) {
          count++;
        }
      }
    }

    return count;
  }

  private int[][] parseTrees(String[] lines) {
    int[][] trees = new int[GRID_SIZE][GRID_SIZE];
    for (int i = 0; i < GRID_SIZE; i++) {
      String line = lines[i];
      for (int j = 0; j < GRID_SIZE; j++) {
        trees[i][j] = line.charAt(j) - 48;
      }
    }
    return trees;
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
    for (int k = curCol + 1; k < GRID_SIZE; k++) { // right side
      if (curTree > trees[curRow][k]) {
        if (k == GRID_SIZE - 1) {
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

    for (int k = curRow + 1; k < GRID_SIZE; k++) { // top side
      if (curTree > trees[k][curCol]) {
        if (k == GRID_SIZE - 1) {
          return true;
        }
      } else {
        break;
      }
    }

    return false;
  }

  @Override
  public Integer part2(String contents) {
    String[] lines = contents.split("\n");
    int[][] trees = parseTrees(lines);
    int max = 0;
    for (int curRow = 1; curRow < GRID_SIZE - 1; curRow++) {
      for (int curCol = 1; curCol < GRID_SIZE - 1; curCol++) {
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
        for (int k = curCol + 1; k < GRID_SIZE; k++) { // right side
          if (curTree > trees[curRow][k] && k < GRID_SIZE - 1) {
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
        for (int k = curRow + 1; k < GRID_SIZE; k++) { // bottom side
          if (curTree > trees[k][curCol] && k < GRID_SIZE - 1) {
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
    return max;
  }
}
