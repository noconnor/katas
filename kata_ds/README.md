## Objective 

1. [Implement a hashtable (use chaining)](src/main/java/com/github/noconnor/reference/ChainedHashTable.java)
2. [Implement a min-heap](src/main/java/com/github/noconnor/reference/MinHeap.java)
3. [Implement a max-heap](src/main/java/com/github/noconnor/reference/MaxHeap.java)
4. [Implement a stack](src/main/java/com/github/noconnor/reference/Stack.java)

<br>

## Algorithms

**Hash table:**

https://algs4.cs.princeton.edu/34hash/

* [SeparateChainingLiteHashST](https://algs4.cs.princeton.edu/34hash/SeparateChainingLiteHashST.java.html)
* [Chained HashTable](http://www.algolist.net/Data_structures/Hash_table/Chaining)

<br>

**Heap:**

* [Binary Heap](https://www.cs.cmu.edu/~adamchik/15-121/lectures/Binary%20Heaps/heaps.html)

A binary heap is a complete binary tree which satisfies the heap ordering property. The ordering can be one of two types:

* the min-heap property: the value of each node is **greater** than or equal to the value of its parent, with the minimum-value element at the root.
* the max-heap property: the value of each node is **less than** or equal to the value of its parent, with the maximum-value element at the root.

A heap is not a sorted structure and can be regarded as partially ordered.

Heap height `O(log n)`

A heap is useful data structure when you need to remove the object with the highest (or lowest) priority. 
A common use of a heap is to implement a priority queue.

The root is the second item in the array. 
We skip the index zero cell of the array for the convenience of implementation. 
Consider k-th element of the array:

* its left child is located at **2\*k** index 
* its right child is located at **2\*k+1** index 
* its parent is located at **k/2** index

<br>

*min-heap*

* Insert
  * Add element to end of heap
  * Compare new element with parent, if less than, swap with parent
  * repeat comparison step until at root
  * Worst case running time = `O(log n)`

* DeleteMin
  * Replace root node with last node in heap
  * decrement heap size
  * bubble down root value to find correct position in tree (i.e. bubble down until child is less than replaced value)

<br>

**Heap sort Complexity**

| Best| Worst | Average |
|:----|:------|:--------|
|O(n*log n)|O(n*log n)|O(n*log n)|

<br>

## Common Complexities

Charts from http://bigocheatsheet.com/

![Complexity Overview](src/main/resources/complexity_chart.png?raw=true)

<br>

![Structure Complexity Overview](src/main/resources/DS_complexities.png?raw=true)

<br>

![Sorting Complexity Overview](src/main/resources/sorting_complexities.png?raw=true)