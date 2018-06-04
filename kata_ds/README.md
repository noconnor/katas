## Objective 

1. [Implement a hashtable (use chaining)](src/main/java/com/github/noconnor/reference/ChainedHashTable.java)
2. Implement a hashtable (use linear probing)
3. [Implement a min-heap](src/main/java/com/github/noconnor/reference/MinHeap.java)
4. [Implement a max-heap](src/main/java/com/github/noconnor/reference/MaxHeap.java)
5. [Implement a stack](src/main/java/com/github/noconnor/reference/Stack.java)
6. [Implement a linked list](src/main/java/com/github/noconnor/reference/LinkedList.java)


<br>

## Algorithms

**Hash table:**

https://algs4.cs.princeton.edu/34hash/

* [SeparateChainingLiteHashST](https://algs4.cs.princeton.edu/34hash/SeparateChainingLiteHashST.java.html)
* [Chained HashTable](http://www.algolist.net/Data_structures/Hash_table/Chaining)

Rue of thumb, resize hash table when ~= 70% full

No ordering in hashtable & resizing is expensive.

HashSet : one "value" stored, common methods `add` and `contains`
HashMap : two values stored, the key and the value, common methods `get` and`put`
 
*Hash functions:*
* K mod n is a common hash function (n is the size of the hash table array), remainder will always be in the range
`0` -> `N-1` 

*Collision handling:*
* linear probing (or open addressing): on collision, find next open spot
* Separate chaining: keep list instead of single element


| Pros | Cons|
|:-----|:----|
|Average `O(1)` insert & remove| Re-sizing cost|
| - | No ordering|


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

**Linked List**

List Versus Linked list
* List insertion time (worst case) O(n)
* Doubly linked list has next and previous pointers, store pointer to head and tail of list
* Singly linked list only has next pointer, typically only store pointer to head
* Sentinel nodes, dummy nodes inserted at start and end of list, don't store any data and always present even when list is empty, pointed too by head and tail references 


## Common Complexities

Charts from http://bigocheatsheet.com/

![Complexity Overview](src/main/resources/complexity_chart.png?raw=true)

<br>

![Structure Complexity Overview](src/main/resources/DS_complexities.png?raw=true)

<br>

![Sorting Complexity Overview](src/main/resources/sorting_complexities.png?raw=true)
