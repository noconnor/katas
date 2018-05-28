package com.github.noconnor.reference;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

import java.util.Arrays;

public class MergeSort {

    private int iterations;

    public void sort(int[] array) {

    }

    public static void main(String[] args) {
        int[] array = new int[]{12, 3, 3, 4, 5, 7, 8, 99, 23, 8};
        MergeSort ms = new MergeSort();
        System.out.println("Before: " + Arrays.toString(array));
        ms.sort(array);
        System.out.println("After: " + Arrays.toString(array) + "[iterations:" + ms.iterations + "][arrayLength: 10]");

        assertThat(ms.iterations, is(36));
        assertThat(array, is(new int[]{3, 3, 4, 5, 7, 8, 8, 12, 23, 99}));
    }

}
