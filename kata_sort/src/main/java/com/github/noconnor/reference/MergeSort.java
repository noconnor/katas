package com.github.noconnor.reference;

import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

import java.util.Arrays;

@SuppressWarnings("Duplicates")
public class MergeSort {

    private int iterations;

    public void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    private void sort(int[] array, int l, int r) {
        if (l < r) {
            // find midpoint
            int m = (l + r) / 2;

            // sort sub arrays
            sort(array, l, m);
            sort(array, m + 1, r);

            // merge sub arrays
            merge(array, l, m, r);
        }
    }

    private void merge(int[] array, int l, int m, int r) {
        int n1 = (m - l) + 1;
        int n2 = r - m;

        // temp arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        // fill temp arrays
        for (int i = 0; i < n1; i++) {
            L[i] = array[l + i];
            iterations++;
        }
        for (int i = 0; i < n2; i++) {
            R[i] = array[m + i + 1];
            iterations++;
        }

        // merge
        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (L[i] < R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
            iterations++;
        }

        // complete merge (copy remaining)
        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
            iterations++;
        }

        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
            iterations++;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{12, 3, 3, 4, 5, 7, 8, 99, 23, 8};
        MergeSort ms = new MergeSort();
        System.out.println("Before: " + Arrays.toString(array));
        ms.sort(array);
        System.out
            .println(format("After: %s [iterations %d] [len %d]", Arrays.toString(array), ms.iterations, array.length));

        assertThat(ms.iterations, is(68));
        assertThat(array, is(new int[]{3, 3, 4, 5, 7, 8, 8, 12, 23, 99}));
    }

}
