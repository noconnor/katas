package com.github.noconnor.reference;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

import java.util.Arrays;

public class QuickSort {

    private int iterations;

    public void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    private void sort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            sort(array, low, pi - 1);
            sort(array, pi + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {

        // scan thru the sub array with index j
        // follow with index i, only incrementing if entry at j is <= than pivot
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                int buffer = array[i];
                array[i] = array[j];
                array[j] = buffer;
            }
            iterations++;
        }
        // move pivot to new location
        int buffer = array[i + 1];
        array[i + 1] = array[high];
        array[high] = buffer;
        // return pivot index
        return i + 1;
    }

    public static void main(String[] args) {
        int[] array = new int[]{12, 3, 3, 4, 5, 7, 8, 99, 23, 8};
        QuickSort qs = new QuickSort();
        System.out.println("Before: " + Arrays.toString(array));
        qs.sort(array);
        System.out.println("After: " + Arrays.toString(array) + "[iterations:" + qs.iterations + "][arrayLength: " + array.length+ "]");

        assertThat(qs.iterations, is(27));
        assertThat(array, is(new int[]{3, 3, 4, 5, 7, 8, 8, 12, 23, 99}));
    }

}
