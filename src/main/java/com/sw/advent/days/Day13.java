package com.sw.advent.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day13 implements Day<Integer> {

  @Override
  public Integer part1(String contents) {
    String[] lines = contents.split("\n");
    int sum = 0;
    for (int i = 0; i < lines.length; i = i + 3) {
      Packet packet1 = parsePacket(lines[i]);
      Packet packet2 = parsePacket(lines[i + 1]);
      if (packet1.compareTo(packet2) < 0) {
        sum += (i / 3 + 1);
      }
    }
    return sum;
  }

  @Override
  public Integer part2(String contents) {
    contents += "\n[[2]]\n[[6]]";
    String[] packetPairs = contents.split("\n\n");
    List<Packet> packets = Arrays.stream(packetPairs)
        .flatMap(pp -> Arrays.stream(pp.split("\n")))
            .map(this::parsePacket)
        .sorted()
        .toList();


    int prod = 1;
    for (int i = 0; i<packets.size(); i++) {
      if ("[[2]]".equals(packets.get(i).toString())) {
        prod *= (i + 1);
      } else if ("[[6]]".equals(packets.get(i).toString())) {
        prod *= (i + 1);
        break;
      }
    }
    return prod;
  }

  Packet parsePacket(String line) {
    char[] chars = line.toCharArray();
    Packet curPacket = null;
    int num = 0;
    for (char c : chars) {
      if (num != 0 && !Character.isDigit(c)) {
        curPacket.data().add(Packet.fromNum(num - 1, curPacket));
        num = 0;
      }
      if ('[' == c) {
        Packet packet = Packet.fromData(curPacket);
        if (curPacket != null) {
          curPacket.data().add(packet);
        }
        curPacket = packet;
      } else if (']' == c) {
        if (curPacket.parent() != null) {
          curPacket = curPacket.parent();
        }
      } else if (Character.isDigit(c)) {
        num = num * 10 + (c - 47);
      }
    }
    return curPacket;
  }


  record Packet(List<Packet> data, int num, Packet parent) implements Comparable<Packet> {

    static Packet fromData(Packet parent) {
      return new Packet(new ArrayList<>(), -1, parent);
    }

    static Packet fromNum(int num, Packet parent) {
      return new Packet(null, num, parent);
    }

    boolean isNum() {
      return num != -1;
    }

    @Override
    public String toString() {
      if (data != null) {
        return "[" + data.stream().map(Packet::toString).collect(Collectors.joining(",")) + "]";
      } else {
        return Integer.toString(num);
      }
    }


    @Override
    public int compareTo(Packet otherPacket) {
      Packet packet1 = this;
      Packet packet2 = otherPacket;
      if (packet1.isNum() && packet2.isNum()) {
        return Integer.compare(packet1.num, packet2.num);
      }

      if (packet1.isNum()) {
        Packet newParent = Packet.fromData(packet1.parent);
        newParent.data.add(Packet.fromNum(packet1.num, newParent));
        packet1 = newParent;
      }
      if (packet2.isNum()) {
        Packet newParent = Packet.fromData(packet2.parent);
        newParent.data.add(Packet.fromNum(packet2.num, newParent));
        packet2 = newParent;
      }

      List<Packet> p1Data = packet1.data();
      List<Packet> p2Data = packet2.data();
      int max = Math.max(p1Data.size(), p2Data.size());
      for (int i = 0; i < max; i++) {
        if (i >= p1Data.size()) {
          return -1;
        } else if (i >= p2Data.size()) {
          return 1;
        }
        int comp = p1Data.get(i).compareTo(p2Data.get(i));
        if (comp != 0) {
          return comp;
        }
      }
      return 0;
    }
  }
}
