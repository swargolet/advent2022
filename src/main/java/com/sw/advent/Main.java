package com.sw.advent;

import com.sw.advent.days.Day;
import com.sw.advent.days.Day1;
import com.sw.advent.days.Day1b;
import com.sw.advent.days.Day2;
import com.sw.advent.file.FileLoader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Main {

  private static final Day DAY = new Day2();

  public static void main(String[] args) throws URISyntaxException, IOException {
    FileLoader fileLoader = new FileLoader();
    File file = fileLoader.getFileFromResource("input/" + DAY.getDay() + ".txt");
    String contents = Files.readString(file.toPath(), StandardCharsets.UTF_8);

    DAY.process(contents);
  }
}
