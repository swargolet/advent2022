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
    int sum = contents.lines()
        .mapToInt(line -> {
          String[] split = line.split(":");
          int gameId = Integer.parseInt(split[0].substring(5));
          String gameData = split[1];

          long count = Arrays.stream(gameData.split("[,;]"))
              .map(dice -> dice.split(" "))
              .filter(roll -> ("green".equals(roll[2]) && Integer.parseInt(roll[1]) > MAX_GREEN)
                  || ("blue".equals(roll[2]) && Integer.parseInt(roll[1]) > MAX_BLUE)
                  || ("red".equals(roll[2]) && Integer.parseInt(roll[1]) > MAX_RED))
              .count();

          return count == 0 ? gameId : 0;
        }).sum();

    // 2716

//
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
//    System.out.println(sum);
//    System.out.println(sum2);
    System.out.println(sum == sum2);
    System.out.println(2716 == sum);
    return sum;
  }


  @Override
  public Integer part2(String contents) {
    return contents.lines()
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
  }
}
