## Objective

* [Implement a binary tree](src/main/java/com/github/noconnor/reference/BinaryTree.java)
* Implement a binary search tree
* Implement an n-ary tree
* Implement a tries tree
* Implement a balanced binary tree (red/black)
* Implement a balanced binary tree (splay)
* Implement a balanced binary tree (AVL)

## Resources

* [Coursera trees](https://www.coursera.org/learn/data-structures-optimizing-performance/lecture/ovovP/core-trees)

## Notes

**General Tree Definitions:**

* Root has no parents
* Leaf has no children
* Each node has only one parent, no cycles
* Binary tree: Each node has at most 2 children
* Generic tree: Each node can have multiple children


**Traversals:**

* Pre-order: visit root, visit all left subtree, visit all right subtree
* Post-order: visit all left subtree, visit all right subtree, visit root
* In-order: visit all left subtree, visit root, visit all right subtree
* Level-order: Breadth First Search (BFS)


**Binary Search tree Definition:**

Insertion/Removal comparable to a linked list with the search capabilities associated with binary search on an ordered array 

* Must be a Binary Tree
* Left subtree needs to be "less than" parent
* Right subtree needs to be "greater than" parent

