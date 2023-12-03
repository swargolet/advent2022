package com.sw.advent.days.y2022;

import com.sw.advent.days.Day;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Y2022D20 implements Day<Long> {

  // Function to move each number in list
  static void moveNumber(int[] list, int n) {
    printArray(list, n);
    int tempArry[] = Arrays.copyOf(list, n);
    // Iterate through list
    for (int i = 0; i < list.length; i++) {
      int value = tempArry[i];

      // Determine the number of positions to move the current value
      int move = 0;
      if (value > 0) {
        move = value % list.length;
      } else {
        move = list.length + (value % list.length);
      }

      // Move value forward or backward
      int tempIndex = Arrays.binarySearch(list, value);
      if (value > 0) {
        // Move value forward
        int temp = list[i];
        for (int j = 0; j < move; j++) {
          int nextIndex = (i + j + 1) % list.length;
          list[i + j] = list[nextIndex];
        }
        list[(i + move) % list.length] = temp;
      } else {
        // Move value backward
        int temp = list[i];
        for (int j = 0; j < move; j++) {
          int nextIndex = (i - j - 1 + list.length) % list.length;
          list[i - j] = list[nextIndex];
        }
        list[(i - move + list.length) % list.length] = temp;
      }
      printArray(list, n);
    }

//  int temp[] = Arrays.copyOf(arr, n);
//    printArray(temp, n);
//    // Move each element of the array
//    for (int i = 0; i < n; i++) {
//      int index = arr[i] % n;
//
//      // If element is negative
//      if (arr[i] < 0) {
//        index = n + index;
//      }
//
//      // Move the element to its index
//      temp[index] = arr[i];
//      printArray(temp, n);
//    }
//
//    // Copy the array to original array
//    for (int i = 0; i < n; i++) {
//      arr[i] = temp[i];
//    }
  }

  /**
   Initial arrangement:
   1 2 -3 3 -2 0 4

   1 moves between 2 and -3:
   i = 0; o = 0; _ 1 _ _ _

   2 moves between -3 and 3:
   i = 1; o = 1; 1 _ 2 _ _     value - (i - o)  2 - (1-1) = 2

   -3 moves between -2 and 0:
   i = 2; o = 3;      value - (i - o)  = -2  if neg length -1 - movement =  7-1+(-2)
   1 2 _ _ -3 _ _

   3 moves between 0 and 4:
   i = 3; o = 0
   1 2 _ -3 _ 3 _

   -2 moves between 4 and 1:
   1, 2, -3, _, 3, _, -2

   0 does not move:
   1, 2, -3, 0, 3, 4, -2

   4 moves between -3 and 0:
   1, 2, -3, 4, 0, 3, -2
   */

  // Function to print the array
  static void printArray(int arr[], int n) {
    for (int i = 0; i < n; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  @Override
  public Long part1(String contents) {
    // Unordered list of random numbers
    String[] split = contents.split("\n");

    Set<String> collect = Arrays.stream(split)
        .collect(Collectors.toSet());
//    int arr[] = Arrays.stream(contents.split("\n")).mapToInt(Integer::valueOf).toArray();
//    int n = arr.length;
//
//    moveNumber(arr, n);
//
//    // Print the mixed array
//    printArray(arr, n);
    return null;
  }
}
