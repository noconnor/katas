package com.github.noconnor.reference;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class InsertionSort {

    private int iterations;

    private void sort(int[] array) {
        // scan forward through array with index i
        // scan backwards through the array with j
        // if any values to the left of i are greater than ith element, shift all values to the right at that point
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int curr = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > curr) {
                array[j + 1] = array[j];
                j--;
                iterations++;
            }
            array[j + 1] = curr;
        }
    }

    private void print(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        InsertionSort sorter = new InsertionSort();
        int[] array = new int[]{12, 11, 13, 5, 6};
        sorter.print(array);
        sorter.sort(array);
        sorter.print(array);
        assertThat(array, is(new int[]{5, 6, 11, 12, 13}));
        assertThat(sorter.iterations, is(7));
    }

}
