package com.github.noconnor.reference;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.HashMap;
import java.util.Map;

public class LinearSearch {

    private int find(int[] array, int targetValue) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == targetValue) {
                return i;
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
            int index = new LinearSearch().find(array, key);
            System.out.println(key + " is at index: " + index);
            assertThat(index, is(value));
        });
    }
}
