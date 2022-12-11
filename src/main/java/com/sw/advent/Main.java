package com.sw.advent;

import com.sw.advent.days.Day;
import com.sw.advent.days.*;
import com.sw.advent.file.FileLoader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Main {

  private static final Day<?> DAY = new Day11();

  public static void main(String[] args) throws URISyntaxException, IOException {
    FileLoader fileLoader = new FileLoader();
    File file = fileLoader.getFileFromResource("input/" + DAY.getDay() + ".txt");
    String contents = Files.readString(file.toPath(), StandardCharsets.UTF_8);

    Object part1 = DAY.part1(contents);
    if (part1 != null) {
      System.out.println(part1);
    }

    Object part2 = DAY.part2(contents);
    if (part2 != null) {
      System.out.println(part2);
    }
  }
}
