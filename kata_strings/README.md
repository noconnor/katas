

## Objective 

Source: https://www.codewars.com/kata/recursive-reverse-string/java

1. Objective is to complete a recursive function reverse() that 
receives `word` as String and returns the same string in 
reverse order
2. Edit distance algorithm: The number of transformations required to turn one string into another string. Implement a spell checker.
3. Implement word path algorithm. Shortest route from one word to a target word by mutating characters in original word. 
<br />

## Reverse String Rules

* reverse function should be executed only N times. N = length of the input string
* helper functions are not allowed
* changing the signature of the function is not allowed

<br />


## Edit Distance Algorithm

```
// BFS
Create queue to hold words to explore
Create set of visited words
Create a list to hold real words

Add original string to queue
while queue is not empty and not enough words generated:
  remove word from head of queue and set to curr
  Get neighours (distance 1 away) from curr
  for each neighbour n
    if n is not in visited:
      add n to visited set
      add n to back of queue
      if n is valid word
        add n to list of real words 
``` 


* Each layer in the tree ~= 52K + 26 entries (where k is number of letters in teh original word) (underestimate as ther will be some words of length k+1 due to addition phase)
* `52K + 26 = (25k substitutions) + (26(k+1) insertions) + (k deletions)`
* Total tree will be `(52K + 26)^n` ~= O(k^n)

Dynamic programming can solve in O(k^2)! (not covered)

Using pruning (restrict to only valid words) to restrict tree size


## Word Path Algorithm

```
// BFS
Create a queue of TreeNode's
Create a set of visited TreeNode's

Create a TreeNode root containing start node
Add start word to visited set
Add TreeNode root to queue

While queue is not empty and word has not been found:
  Remove head of queue and set to curr
  Find all neighbours of curr (distance of 1 away) <-- prune non real words
  for each neightbour n
    if n is not in visited set
      add n as a child of curr
      add n to the visisted set
      add n to the queue
      if n is the target word
        return path to n
```

```
TreeNode class
 TreeNode parent
 String word
 List<TreeNode> children
 
 TreeNode(String word, parent)
 addChild(TreeNode)
 getPath() -> follow path to parent
 
```

<br />


