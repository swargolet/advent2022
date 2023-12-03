package com.sw.advent.days;

public interface Day<T> {

  default String getYear() {
    String simpleName = this.getClass().getSimpleName();
    return simpleName.substring(1,5);
  }

  default String getDay() {
    String simpleName = this.getClass().getSimpleName();

    String longDay = simpleName.substring(6, 8).stripLeading();
    if (longDay.startsWith("0")) {
      return longDay.substring(1, 2);
    }
    return longDay;
//    if (Character.isAlphabetic(simpleName.charAt(simpleName.length() - 1))) {
//      return simpleName.substring(0, simpleName.length() - 1);
//    }
//    return simpleName;
  }

  default T part1(String contents) {
    return null;
  }

  default T part2(String contents) {
    return null;
  }
}
