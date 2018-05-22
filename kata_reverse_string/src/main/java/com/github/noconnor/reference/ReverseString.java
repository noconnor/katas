package com.github.noconnor.reference;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReverseString {

  private int iterations = 0;

  private String reverseNonRecursive(String word) {
    StringBuilder builder = new StringBuilder();
    for (int i = word.length() - 1; i >= 0; i--) {
      iterations++;
      builder.append(word.charAt(i));
    }
    return builder.toString();
  }

  private String reverseSimple(String word) {
    iterations++;
    boolean complete = (word == null) || (word.length() <= 1);
    return complete ? word : reverseSimple(word.substring(1)) + word.charAt(0);
  }

  private String reverseLowMemory(char[] word) {
    int startIndex = iterations;
    int midPoint = word.length / 2;
    // subtract 1 for zero indexing
    int finalIndex = (word.length - 1) - iterations;
    // iterations must be LESS then midpoint to account for zero indexing
    if (iterations < midPoint) {
      iterations++;
      char buffer = word[startIndex];
      word[startIndex] = word[finalIndex];
      word[finalIndex] = buffer;
      return reverseLowMemory(word);
    } else {
      return new String(word);
    }
  }

  private ReverseString reset() {
    iterations = 0;
    return this;
  }

  public static void main(String[] args) {

    Map<String, String> testData = new HashMap<>();
    testData.put("Hello World", "dlroW olleH");
    testData.put("abcd", "dcba");
    testData.put("12345", "54321");

    ReverseString reverser = new ReverseString();

    testData.forEach((key, value) -> {
      String reverse = reverser.reset().reverseSimple(key);
      System.out.println(reverse + " [iterations: " + reverser.iterations + "] [Expected: '" + value + "']");
      assertThat(reverse, is(value));
    });

    System.out.println();

    testData.forEach((key, value) -> {
      String reverse = reverser.reset().reverseLowMemory(key.toCharArray());
      System.out.println(reverse + " [iterations: " + reverser.iterations + "] [Expected: '" + value + "']");
      assertThat(reverse, is(value));
    });

    System.out.println();

    testData.forEach((key, value) -> {
      reverser.reset();
      String reverse = reverser.reset().reverseNonRecursive(key);
      System.out.println(reverse + " [iterations: " + reverser.iterations + "] [Expected: '" + value + "']");
      assertThat(reverse, is(value));
    });
  }

}
