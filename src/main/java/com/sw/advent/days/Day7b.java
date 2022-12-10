package com.sw.advent.days;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import lombok.Data;

public class Day7b implements Day<Long> {

  private static Stream<Resource> flatten(Resource resource) {
    return Stream.concat(
        Stream.of(resource),
        resource.getChildren().values().stream().flatMap(Day7b::flatten));
  }

  public void process(String contents) {
    Resource root = parseTree(contents);

    // Part 1
    long sum = flatten(root)
        .mapToLong(Resource::getTotalSize)
        .filter(l -> l <= 100000)
        .sum();
    System.out.println(sum);

    // Part 2
    long neededSpace = 30000000 - (70000000 - root.getTotalSize());
    long dirToDelete = flatten(root)
        .mapToLong(Resource::getTotalSize)
        .filter(l -> l >= neededSpace)
        .sorted()
        .limit(1)
        .findFirst()
        .orElseThrow();
    System.out.println(dirToDelete);
  }

  private Resource parseTree(String contents) {
    String[] lines = contents.split("\n");

    Resource root = new Resource("/", null);
    Resource curResource = root;

    for (int i = 1; i < lines.length; i++) {
      String[] s = lines[i].split(" ");

      if ("$".equals(s[0])) {
        if ("cd".equals(s[1])) {
          curResource = ("..".equals(s[2])) ? curResource.getParent() : curResource.getChildren().get(s[2]);
        }
      } else if ("dir".equals(s[0])) {
        Resource resource = new Resource(s[1], curResource);
        curResource.getChildren().put(resource.getName(), resource);
      } else {
        curResource.setSize(curResource.getSize() + Long.parseLong(s[0]));
      }
    }
    return root;
  }

  @Data
  static class Resource {
    private String name;
    private Resource parent;
    private Map<String, Resource> children = new HashMap<>();
    private long size = 0; // immediateSize
    private long totalSize = 0;

    public Resource(String name, Resource parent) {
      this.parent = parent;
      this.name = name;
    }

    public long getTotalSize() {
      if (totalSize == 0) {
        totalSize = children.values().stream()
            .mapToLong(Resource::getTotalSize)
            .sum();
      }
      return totalSize + size;
    }

    public long getSize() {
      return size;
    }
  }
}
