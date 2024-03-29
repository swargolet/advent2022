package com.sw.advent.days.y2022;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import com.sw.advent.days.Day;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.NonFinal;

public class Y2022D07 implements Day<Long> {
  public Long part1(String contents) {
    Resource root = parseTree(contents);
    return flatten(root)
        .filter(Resource::isDirectory)
        .mapToLong(Resource::getSize)
        .filter(l -> l <= 100000)
        .sum();
  }

  public Long part2(String contents) {
    Resource root = parseTree(contents);
    long neededSpace = 30000000 - (70000000 - root.getSize());
    return flatten(root)
        .filter(Resource::isDirectory)
        .mapToLong(Resource::getSize)
        .filter(l -> l >= neededSpace)
        .sorted()
        .limit(1)
        .findFirst()
        .orElseThrow();
  }

  private static Stream<Resource> flatten(Resource resource) {
    return Stream.concat(
        Stream.of(resource),
        resource.getChildren().values().stream().flatMap(Y2022D07::flatten));
  }

  private Resource parseTree(String contents) {
    String[] lines = contents.split("\n");

    Resource root = Resource.builder().name("/").type(ResourceType.DIRECTORY).build();
    Resource curResource = root;
    for (int i = 1; i < lines.length; i++) {
      String line = lines[i];
      String[] s = line.split(" ");

      if ("$".equals(s[0])) {
        if ("cd".equals(s[1])) {
          curResource = ("..".equals(s[2])) ? curResource.getParent() : curResource.getChildren().get(s[2]);
        }
      } else {
        Resource resource = Resource.fromLine(line).toBuilder().parent(curResource).build();
        curResource.getChildren().put(resource.getName(), resource);
      }
    }
    return root;
  }

  enum ResourceType {
    DIRECTORY,
    FILE
  }

  @Value
  @Builder(toBuilder = true)
  static class Resource {
    String name;
    Resource parent;
    @Builder.Default
    Map<String, Resource> children = new HashMap<>();
    ResourceType type;
    @NonFinal
    long size;

    boolean isDirectory() {
      return type.equals(ResourceType.DIRECTORY);
    }

    static Resource fromLine(String line) {
      String[] parts = line.split(" ");
      boolean isDir = "dir".equals(parts[0]);
      return Resource.builder()
        .type(isDir ? ResourceType.DIRECTORY : ResourceType.FILE)
        .name(parts[1])
        .size(isDir ? 0 : Long.parseLong(parts[0]))
        .build();
    }

    public long getSize() {
      if (size == 0) {
        size = children.values().stream()
            .mapToLong(Resource::getSize)
            .sum();
      }
      return size;
    }
  }

  private static void printResource(Resource r, int level) {
    StringBuilder sb = new StringBuilder();
    sb.append("\t".repeat(Math.max(0, level)));
    sb.append("- ");
    sb.append(r.getName());
    sb.append(" (");
    sb.append(r.isDirectory() ? "dir" : "file");
    sb.append(", size=");
    sb.append(r.getSize());
    sb.append(")");
    System.out.println(sb);
    if (r.getChildren() != null) {
      r.getChildren().values().forEach(c -> printResource(c, level + 1));
    }
  }
}
