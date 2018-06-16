package com.github.noconnor.reference;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

@SuppressWarnings("Duplicates")
public class MaxHeap<E extends Comparable<? super E>> {

    private E[] heap;
    private int size;

    public MaxHeap(int capacity) {
        this.heap = (E[]) new Comparable[capacity - 1];
        this.size = 0;
    }

    public void insert(E value) {

        if (size == heap.length) {
            throw new IllegalStateException("No space left");
        }

        // add item to bottom of the heap
        int pos = ++size;

        // percolate item up to right position
        for (; pos > 1 && value.compareTo(heap[pos/2]) > 0; pos = pos / 2) {
            heap[pos] = heap[pos/2];
        }

        heap[pos] = value;
    }

    public E deleteMax() {
        E max = heap[1];
        heap[1] = heap[size];
        heap[size] = null;
        size--;

        percolateDown(1);
        return max;
    }

    public void heapSort(E[] array){
        heap = (E[]) new Comparable[array.length + 1];
        size = array.length;
        System.arraycopy(array, 0, heap, 1, array.length);
        buildHeap();

        for(int i = size; i > 0; i--){
            E temp = heap[1];
            heap[1] = heap[i];
            heap[i] = temp;
            size--;
            percolateDown(1);
        }

        // heap.length - 1 because heap is 1 bigger than the array
        for(int i = 0; i < heap.length - 1; i++){
            array[i] = heap[heap.length - 1 - i];
        }
    }

    private void buildHeap(){
        for(int k = size/2; k > 0; k--){
            percolateDown(k);
        }
    }

    private void percolateDown(int index) {
        E valueAtIndex = heap[index];
        int childIndex;
        int parentIndex = index;

        for (; 2 * parentIndex < size; parentIndex = childIndex) {

            childIndex = 2 * parentIndex;

            // find largest child
            if (childIndex != size && heap[childIndex + 1].compareTo(heap[childIndex]) > 0) {
                childIndex++;
            }

            // swap parentIndex with largest child
            if(valueAtIndex.compareTo(heap[childIndex]) < 0) {
                heap[parentIndex] = heap[childIndex];
            } else {
                break;
            }
        }

        heap[parentIndex] = valueAtIndex;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("\n");
        reverseInOrder(1, 0, builder);
        return builder.toString();
    }

    private void reverseInOrder(int index, int level, StringBuilder builder) {
        if (index > 0 && index <= size) {
            int nextLevel = level + 1;

            reverseInOrder(2 * index + 1, nextLevel, builder);
            if (level == 0) {
                builder.append("(");
                builder.append(heap[index]);
                builder.append(")\n");
            } else {
                for (int i = 0; i < level - 1; i++) {
                    builder.append(" |\t");
                }
                builder.append(" |-(");
                builder.append(heap[index]);
                builder.append(")\n");
            }
            reverseInOrder(2 * index, nextLevel, builder);
        }
    }

    public static void main(String[] args) {
        MaxHeap<Integer> heap = new MaxHeap<>(100);
        heap.insert(44);
        heap.insert(4);
        heap.insert(109);
        heap.insert(1);
        heap.insert(22);
        heap.insert(17);


        System.out.println(heap);
        heap.deleteMax();
        System.out.println(heap);

        Integer[] sortable = new Integer[]{4,55,6,3,2,66,77,7,8,1,1};
        heap.heapSort(sortable);
        assertThat(sortable, is(new Integer[]{77, 66, 55, 8, 7, 6, 4, 3, 2, 1, 1}));

    }

}
