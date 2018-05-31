package com.github.noconnor.reference;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

import java.util.Arrays;

@SuppressWarnings("Duplicates")
public class MinHeap<E extends Comparable<? super E>> {

    private E[] heap;
    private int size = 0;

    public MinHeap(int capacity) {
        heap = (E[]) new Comparable[capacity - 1];
    }

    public MinHeap(E[] array) {
        this.heap = (E[]) new Comparable[array.length + 1];
        this.size = array.length;
        System.arraycopy(array, 0, heap, 1, array.length);
        buildHeap();
    }

    public void insert(E value) {
        if (size == heap.length - 1) {
            // increase heap size
            throw new IllegalStateException("No more space in heap");
        }

        // Add value to end of heap
        int pos = ++size;

        // percolate up to find position in heap
        // pos/2 = parent node location
        for (; pos > 1 && value.compareTo(heap[pos / 2]) < 0; pos = pos / 2) {
            heap[pos] = heap[pos / 2];
        }
        heap[pos] = value;
    }

    public E deleteMin() {
        // In a min heap, root will be min
        E min = heap[1];
        heap[1] = heap[size];
        heap[size] = null;
        size--;
        percolateDown(1);
        return min;
    }

    public void heapSort(E[] array) {
        size = array.length;
        heap = (E[]) new Comparable[array.length + 1];
        System.arraycopy(array, 0, heap, 1, array.length);
        buildHeap();

        for (int i = size; i > 0; i--) {
            E temp = heap[i];
            heap[i] = heap[1];
            heap[1] = temp;
            size--;
            percolateDown(1);
        }

        // heap.length - 1 because heap is 1 bigger than the array
        for (int k = 0; k < heap.length - 1; k++) {
            array[k] = heap[heap.length - 1 - k];
        }
    }


    private void buildHeap() {
        // (size / 2) will be the parent of the last entry in the heap
        for (int k = size / 2; k > 0; k--) {
            percolateDown(k);
        }
    }

    private void percolateDown(int index) {

        E valueAtIndex = heap[index];
        int childIndex;
        int parentIndex = index;

        for (; 2 * parentIndex <= size; parentIndex = childIndex) {
            childIndex = 2 * parentIndex;

            // find index of smaller of left & right child
            if (childIndex != size && heap[childIndex].compareTo(heap[childIndex + 1]) > 0) {
                childIndex++;
            }

            // Move the child node up if it is smaller than the value at the parent index
            if (valueAtIndex.compareTo(heap[childIndex]) > 0) {
                heap[parentIndex] = heap[childIndex];
            } else {
                break;
            }
        }

        heap[parentIndex] = valueAtIndex;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("\n");
        inOrder(1, builder, 0);
        return builder.toString();
    }

    private void inOrder(int nodeIndex, StringBuilder builder, int level) {
        if (nodeIndex < heap.length && heap[nodeIndex] != null) {

            int nextLevel = level + 1;

            inOrder(2 * nodeIndex + 1, builder, nextLevel);
            if (level == 0) {
                builder.append("(");
                builder.append(heap[nodeIndex]);
                builder.append(")\n");
            } else {
                for (int i = 0; i < level - 1; i++) {
                    builder.append(" |\t");
                }
                builder.append(" |-(");
                builder.append(heap[nodeIndex]);
                builder.append(")\n");
            }
            inOrder(2 * nodeIndex, builder, nextLevel);

        }
    }

    public static void main(String[] args) {
        MinHeap<Integer> heap = new MinHeap<>(100);
        heap.insert(22);
        heap.insert(34);
        heap.insert(3);
        heap.insert(2);
        heap.insert(10);
        heap.insert(14);

        System.out.println(heap);
        heap.deleteMin();
        System.out.println(heap);

        MinHeap<String> stringHeap = new MinHeap<>(new String[]{"d", "z", "a", "f", "b", "y", "a"});
        System.out.println(stringHeap);

        MinHeap<Integer> sorter = new MinHeap<>(100);
        Integer[] ints = new Integer[]{55, 66, 7, 33, 4, 21, 9, 6, 1, 1};
        sorter.heapSort(ints);
        System.out.println(Arrays.asList(ints));

        assertThat(ints, is(new Integer[]{1, 1, 4, 6, 7, 9, 21, 33, 55, 66}));
    }

}
