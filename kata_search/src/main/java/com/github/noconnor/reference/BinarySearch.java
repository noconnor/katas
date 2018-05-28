package com.github.noconnor.reference;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BinarySearch {

  private int find(int[] array, int targetValue) {

    if (array == null) {
      return -1;
    }

    int L = 0;
    int R = array.length - 1;

    while (L <= R) {
      // greatest integer less than this value (floor midpoint)
      int m = (L + R) / 2;

      if (array[m] < targetValue) {
        L = m + 1;
      } else if (array[m] > targetValue) {
        R = m - 1;
      } else if (array[m] == targetValue) {
        return m;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    int[] array = new int[]{1, 3, 4, 6, 8, 9, 11};

    Map<Integer, Integer> test = new HashMap<>();
    test.put(4, 2);
    test.put(9, 5);
    test.put(11, 6);
    test.put(1, 0);
    test.put(7, -1);
    test.put(2, -1);

    test.forEach((key, value) -> {
      int index = new BinarySearch().find(array, key);
      System.out.println(key + " is at index: " + index);
      assertThat(index, is(value));
    });
  }

}
