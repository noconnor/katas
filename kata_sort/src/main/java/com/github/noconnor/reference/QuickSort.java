package com.github.noconnor.reference;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

import java.util.Arrays;

public class QuickSort {

    private int iterations;

    public void sort(int[] array) {

    }

    public static void main(String[] args) {
        int[] array = new int[]{12, 3, 3, 4, 5, 7, 8, 99, 23, 8};
        QuickSort qs = new QuickSort();
        System.out.println("Before: " + Arrays.toString(array));
        qs.sort(array);
        System.out.println("After: " + Arrays.toString(array) + "[iterations:" + qs.iterations + "][arrayLength: 10]");

        assertThat(qs.iterations, is(36));
        assertThat(array, is(new int[]{3, 3, 4, 5, 7, 8, 8, 12, 23, 99}));
    }


}
