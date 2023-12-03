package com.sw.advent.days.y2023;

import com.sw.advent.days.Day;

import java.util.Arrays;


public class Y2023D02 implements Day<Integer> {

  //  only 12 red cubes, 13 green cubes, and 14 blue cubes
  private static final int MAX_RED = 12;
  private static final int MAX_GREEN = 13;
  private static final int MAX_BLUE = 14;

  //  record Game(int id, int blue)
  @Override
  public Integer part1(String contents) {
    // Stirng.split
    int sum = contents.lines()
        .mapToInt(line -> {
          String[] split = line.split(":");
          int gameId = Integer.parseInt(split[0].substring(5));
          String gameData = split[1];

          boolean isImpossible = Arrays.stream(gameData.split("[,;]"))
              .map(dice -> dice.split(" "))
              .anyMatch(roll -> ("green".equals(roll[2]) && Integer.parseInt(roll[1]) > MAX_GREEN)
                  || ("blue".equals(roll[2]) && Integer.parseInt(roll[1]) > MAX_BLUE)
                  || ("red".equals(roll[2]) && Integer.parseInt(roll[1]) > MAX_RED));

          return isImpossible ? 0 : gameId;
        }).sum();

    // by character
    int sum2 = contents.lines()
        .mapToInt(line -> {
          String[] split = line.split(":");
          int gameId = Integer.parseInt(split[0].substring(5));

          int curNum = 0;
          String gameData = split[1];
          for (char c : gameData.toCharArray()) {
            if (Character.isDigit(c)) {
              curNum = curNum * 10 + c - '0';
            } else if (c == 'r' || c == 'g' || c == 'b') {
              if ((c == 'r' && curNum > MAX_RED) || (c == 'g' && curNum > MAX_GREEN) || (c == 'b' && curNum > MAX_BLUE)) {
                return 0;
              }
              curNum = 0;
            }
          }
          return gameId;
        }).sum();
    System.out.println(sum == sum2);
    return sum;
  }


  @Override
  public Integer part2(String contents) {
    // Stirng.split
    int sum = contents.lines()
        .map(line -> Arrays.stream(line.split(":")[1].split("[,;]"))
            .map(dice -> dice.split(" "))
            .reduce(new int[3], (cubeAry, roll) -> { // red = 0, blue = 1, green = 2
              cubeAry[roll[2].length() - 3] = Math.max(cubeAry[roll[2].length() - 3], Integer.parseInt(roll[1]));
              return cubeAry;
            }, (a, b) -> a))
        .mapToInt(cubeAry -> cubeAry[0] * cubeAry[1] * cubeAry[2])
        .sum();

    // By character
    int sum2 = contents.lines()
        .mapToInt(line -> {
          String gameData = line.split(":")[1];
          int maxRed = 0;
          int maxBlue = 0;
          int maxGreen = 0;
          int curNum = 0;
          for (char c : gameData.toCharArray()) {
            if (Character.isDigit(c)) {
              curNum = curNum * 10 + c - '0';
            } else if (c == 'r' || c == 'g' || c == 'b') {
              if ((c == 'r' && curNum > maxRed)) {
                maxRed = curNum;
              } else if (c == 'g' && curNum > maxGreen) {
                maxGreen = curNum;
              } else if (c == 'b' && curNum > maxBlue) {
                maxBlue = curNum;
              }
              curNum = 0;
            }
          }
          return maxRed * maxGreen * maxBlue;
        }).sum();
    System.out.println(sum == sum2);
    return sum;
  }
}
