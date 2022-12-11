package com.sw.advent.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;

public class Day11 implements Day<Long> {

  @Override
  public Long part1(String contents) {
    long product =  doCycles(contents, 20, 3);
    System.out.println(product == 58056);
    return product;
  }

  @Override
  public Long part2(String contents) {
    long product = doCycles(contents, 10000, 1);
    System.out.println(product == 15048718170L);
    return product;
  }

  public Long doCycles(String contents, int numCycles, int divisor) {
    Map<Integer, Monkey> monkeys = new HashMap<>();
    for (String monkey : contents.split("\n\n")) {
      String[] monkeyItems = monkey.split("\n");

      List<Long> items = Arrays.stream(monkeyItems[1].split(":")[1].split(","))
          .map(String::trim)
          .map(Long::parseLong)
          .collect(Collectors.toCollection(ArrayList::new));

      String opNumStr = monkeyItems[2].substring(25);

      Monkey m = Monkey.builder()
          .items(items)
          .op(monkeyItems[2].charAt(23))
          .opNum("old".equals(opNumStr) ? -1 : Integer.parseInt(opNumStr))
          .divisible(Integer.parseInt(monkeyItems[3].substring(21)))
          .trueMonkey(monkeyItems[4].charAt(29) - 48)
          .falseMonkey(monkeyItems[5].charAt(30) - 48)
          .inspectedTimes(0)
          .build();
      monkeys.put(monkeyItems[0].charAt(7) - 48, m);
    }

    long modulo = monkeys.values().stream().map(Monkey::getDivisible).reduce(1, (a, b) -> a * b);
    for (int i = 0; i < numCycles; i++) {
      for (Map.Entry<Integer, Monkey> e : monkeys.entrySet()) {
        Monkey m = e.getValue();
        for (Long item : m.getItems()) {
          m.setInspectedTimes(m.getInspectedTimes() + 1);
          long newWorryLevel = doOperation(m, item) / divisor;
          int mToPut = (newWorryLevel % m.getDivisible() == 0) ? m.getTrueMonkey() : m.getFalseMonkey();
          monkeys.get(mToPut).getItems().add(newWorryLevel % modulo);
        }
        m.getItems().clear();
      }
    }

    return monkeys.values().stream()
        .mapToLong(Monkey::getInspectedTimes)
        .sorted()
        .skip(monkeys.size() - 2)
        .limit(2)
        .reduce(1, (a, b) -> a * b);
  }

  long doOperation(Monkey m, long item) {
    long num = -1 == m.getOpNum() ? item : m.getOpNum();
    return switch (m.getOp()) {
      case '+' -> num + item;
      case '*' -> num * item;
      default -> throw new IllegalStateException("unexpected op");
    };
  }

  @Data
  @Builder
  static class Monkey {
    private List<Long> items;
    private char op;
    private int opNum;
    private int divisible;
    private int trueMonkey;
    private int falseMonkey;
    private long inspectedTimes;
  }
}
