package com.github.noconnor.reference;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

import java.util.Arrays;

public class BubbleSort {

    private int iterations;

    public void sort(int[] array) {
        int n = array.length;
        iterations = 0;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (array[i - 1] > array[i]) {
                    int buffer = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = buffer;
                    swapped = true;
                }
                iterations++;
            }

        } while (swapped);
    }

    public static void main(String[] args) {

        int[] array = new int[]{12, 3, 3, 4, 5, 7, 8, 99, 23, 8};

        BubbleSort bs = new BubbleSort();
        System.out.println("Before: " + Arrays.toString(array));
        bs.sort(array);
        System.out.println("After: " + Arrays.toString(array) + "[iterations:" + bs.iterations + "][arrayLength: 10]");

        assertThat(bs.iterations, is(36));
        assertThat(array, is(new int[]{3, 3, 4, 5, 7, 8, 8, 12, 23, 99}));
    }
}
