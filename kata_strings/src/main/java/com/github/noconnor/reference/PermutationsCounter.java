package com.github.noconnor.reference;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.HashMap;
import java.util.Map;

public class PermutationsCounter {

    public static int uniquePermutations(String phrase) {
        return factorial(phrase.length()) / factorial(characterCounts(phrase));
    }

    private static Map<Character, Integer> characterCounts(String phrase) {
        Map<Character, Integer> counts = new HashMap<>();
        for (Character c : phrase.toCharArray()) {
            int occurrences = counts.containsKey(c) ? counts.get(c) + 1 : 1;
            counts.put(c, occurrences);
        }
        return counts;
    }

    private static int factorial(Map<Character, Integer> counts) {
        int factorial = 1;
        for (Integer count : counts.values()) {
            factorial = factorial * factorial(count);
        }
        return factorial;
    }

    private static int factorial(int n) {
        return n > 1 ? n * factorial(n - 1) : n;
    }

    public static void main(String[] args) {
        assertThat(uniquePermutations("ABA"), is(3));
        assertThat(uniquePermutations("ybghjhbuytb"), is(1663200));
        assertThat(uniquePermutations("AB"), is(2));
        assertThat(uniquePermutations("ABC"), is(6));
        assertThat(uniquePermutations("ABCA"), is(12));
    }

}
