package com.github.noconnor.reference;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class SelectionSort {

    private int iterations;

    private void sort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minValueIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minValueIndex]) {
                    minValueIndex = j;
                }
                iterations++;
            }

            int temp = array[i];
            array[i] = array[minValueIndex];
            array[minValueIndex] = temp;
        }
    }

    private void print(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SelectionSort sort = new SelectionSort();
        int[] array = new int[]{64, 25, 12, 22, 11};
        sort.print(array);
        sort.sort(array);
        sort.print(array);
        assertThat(array, is(new int[]{11, 12, 22, 25, 64}));
        assertThat(sort.iterations, is(10));
    }

}
