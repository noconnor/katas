## Objective

* [Implement a binary tree](src/main/java/com/github/noconnor/reference/BinaryTree.java)
* [Implement a binary search tree](src/main/java/com/github/noconnor/reference/BinarySearchTree.java)
* [Implement a trie tree](src/main/java/com/github/noconnor/reference/trie/StringTrie.java)

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

#### General Tree Definitions:

* Root has no parents
* Leaf has no children
* Each node has only one parent, no cycles
* Binary tree: Each node has at most 2 children
* Generic tree: Each node can have multiple children
* Max distance to leaves = height of tree

<br> 

#### Traversals:

* Pre-order: visit root, visit all left subtree, visit all right subtree
* Post-order: visit all left subtree, visit all right subtree, visit root
* In-order: visit all left subtree, visit root, visit all right subtree
* Level-order: Breadth First Search (BFS)

<br> 

#### Binary Search tree Definition:

Insertion/Removal comparable to a linked list with the search capabilities associated with binary search on an ordered array 

* Must be a Binary Tree
* Left subtree needs to be "less than" parent
* Right subtree needs to be "greater than" parent

<br> 

#### Balanced BST:

* |LeftHeight - RightHeight| <= 1
* Height ~= `log(n)` 

<br> 

#### Tries:

* Comes from reTRIEval
* Structure of the key helps identify where the value is
* Not a BST (can have more than two child nodes)

<br> 

#### Red/black tree:

https://www.geeksforgeeks.org/red-black-tree-set-1-introduction-2/
 
* Self balancing **binary search tree**
* Every node has a color either red or black.
* Root of tree is always black.
* There are no two adjacent red nodes (A red node cannot have a red parent or red child).
* Every path from root to a NULL node has same number of black nodes.
* height always `log(n)`

Insert: https://en.wikipedia.org/wiki/Red%E2%80%93black_tree#Insertion

Removal: https://en.wikipedia.org/wiki/Red%E2%80%93black_tree#Removal


*Comparison with AVL Tree*

The AVL trees are more balanced compared to Red-Black Trees, but they may cause more rotations during insertion and deletion. 
So if your application involves many frequent insertions and deletions, then Red Black trees should be preferred. 
And if the insertions and deletions are less frequent and search is a more frequent operation, then AVL tree should be preferred over Red-Black Tree.
