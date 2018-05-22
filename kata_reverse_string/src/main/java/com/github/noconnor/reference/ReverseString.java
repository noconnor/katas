package com.github.noconnor.reference;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReverseString {

  private static int N = 0;

  private static String reverseNonRecursive(String word) {
    StringBuilder builder = new StringBuilder();
    for (int i = word.length() - 1; i >= 0; i--) {
      N++;
      builder.append(word.charAt(i));
    }
    return builder.toString();
  }

  private static String reverseSimple(String word) {
    N++;
    boolean complete = (word == null) || (word.length() <= 1);
    return complete ? word : reverseSimple(word.substring(1)) + word.charAt(0);
  }

  private static String reverseLowMemory(char[] word) {
    int startIndex = N;
    int midPoint = word.length / 2;
    // subtract 1 for zero indexing
    int finalIndex = (word.length - 1) - N;
    // N must be LESS then midpoint to account for zero indexing
    if (N < midPoint) {
      N++;
      char buffer = word[startIndex];
      word[startIndex] = word[finalIndex];
      word[finalIndex] = buffer;
      return reverseLowMemory(word);
    } else {
      return new String(word);
    }
  }

  private static void reset() {
    N = 0;
  }

  public static void main(String[] args) {

    Map<String, String> testData = new HashMap<>();
    testData.put("Hello World", "dlroW olleH");
    testData.put("abcd", "dcba");
    testData.put("12345", "54321");

    testData.forEach((key, value) -> {
      String reverse = reverseSimple(key);
      System.out.println(reverse + " [iterations: " + N + "] [Expected: '" + value + "']");
      assertThat(reverse, is(value));
      reset();
    });

    System.out.println();

    testData.forEach((key, value) -> {
      String reverse = reverseLowMemory(key.toCharArray());
      System.out.println(reverse + " [iterations: " + N + "] [Expected: '" + value + "']");
      assertThat(reverse, is(value));
      reset();
    });

    System.out.println();

    testData.forEach((key, value) -> {
      String reverse = reverseNonRecursive(key);
      System.out.println(reverse + " [iterations: " + N + "] [Expected: '" + value + "']");
      assertThat(reverse, is(value));
      reset();
    });
  }

}
