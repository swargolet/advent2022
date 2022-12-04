package com.sw.advent.days;

public interface Day {

  default String getDay() {
    String simpleName = this.getClass().getSimpleName();
    int bIndex = simpleName.indexOf('b');
    if (bIndex != -1) {
      return simpleName.substring(0, simpleName.length() - 1);
    }
    return simpleName;
  }

  default void process(String contents) {
  }
}
