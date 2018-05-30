## Objective

* [Implement a binary tree](src/main/java/com/github/noconnor/reference/BinaryTree.java)
* [Implement a binary search tree](src/main/java/com/github/noconnor/reference/BinarySearchTree.java)
* Implement a tries

*Review:*
* n-ary tree implementation
* red/black tree implementation
* splay tree implementation
* AVL tree implementation

<br> 
## Resources

* [Coursera trees](https://www.coursera.org/learn/data-structures-optimizing-performance/lecture/ovovP/core-trees)

<br> 

## Complexity

| Tree | Best case | Average case | Worst case |
|:-----|:----------|:-------------|:-----------|
| BST           | O(1)      | O(log n) | O(n)       |
| Balanced BST  | O(1)      | O(log n) | O(log n)   |
| Linkedlist    | O(1)      | O(n)     | O(n)       |


***_TreeSet is an example of a balanced binary search tree_

<br> 

## Notes

**General Tree Definitions:**

* Root has no parents
* Leaf has no children
* Each node has only one parent, no cycles
* Binary tree: Each node has at most 2 children
* Generic tree: Each node can have multiple children
* Max distance to leaves = height of tree

<br> 

**Traversals:**

* Pre-order: visit root, visit all left subtree, visit all right subtree
* Post-order: visit all left subtree, visit all right subtree, visit root
* In-order: visit all left subtree, visit root, visit all right subtree
* Level-order: Breadth First Search (BFS)

<br> 

**Binary Search tree Definition:**

Insertion/Removal comparable to a linked list with the search capabilities associated with binary search on an ordered array 

* Must be a Binary Tree
* Left subtree needs to be "less than" parent
* Right subtree needs to be "greater than" parent

<br> 

**Balanced BST:**

* |LeftHeight - RightHeight| <= 1
* Heights ~= `log(n)` 

<br> 

**Tries:**

* Comes from reTRIEval
* Structure of the key helps identify where the value is
* Not a BST (can have more than two child nodes)
