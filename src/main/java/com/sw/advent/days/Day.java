package com.sw.advent.days;

public interface Day<T> {

  default String getDay() {
    String simpleName = this.getClass().getSimpleName();
    if (Character.isAlphabetic(simpleName.charAt(simpleName.length() - 1))) {
      return simpleName.substring(0, simpleName.length() - 1);
    }
    return simpleName;
  }

  default T part1(String contents) {
    return null;
  }

  default T part2(String contents) {
    return null;
  }
}
