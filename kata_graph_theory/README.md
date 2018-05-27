## Objective 

1. [Implement a Graph using an Adjacency Matrix](src/main/java/com/github/noconnor/reference/AdjacencyMatrixGraph.java)
2. [Implement a Graph using an Adjacency List](src/main/java/com/github/noconnor/reference/AdjacencyListGraph.java)
3. Implement a DFS algorithm
4. Implement a BFS algorithm
5. Implement the Dijkstra search algorithm
6. Implement the A* search algorithm


## Reference:

Coursera:

* [Adjacency Matrix/List](https://www.coursera.org/learn/advanced-data-structures/supplement/EL8R1/week-1-additional-resources)
* [DFS and BFS](https://www.coursera.org/learn/advanced-data-structures/supplement/4P3oy/week-2-additional-resources)


## Algorithms

| DFS (Depth First Search) | BFS (Breadth First Search) |
|:----- |:----- |
|<br>DFS(S, G)<br>&nbsp;&nbsp;&nbsp;&nbsp;initialise **stack**, hashset, map<br>&nbsp;&nbsp;&nbsp;&nbsp;push S to stack<br>&nbsp;&nbsp;&nbsp;&nbsp;add S to visited<br>&nbsp;&nbsp;&nbsp;&nbsp;while stack is not empty<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;pop curr from stack<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if curr == G return map<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;foreach of currs unvisited neighbours, n:<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;add n to visited<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;add curr as n's parent in parent map<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;push n to top of stack<br>| <br>BFS(S, G)<br>&nbsp;&nbsp;&nbsp;&nbsp;initialise **queue**, hashset, map<br>&nbsp;&nbsp;&nbsp;&nbsp;enqueue S to queue<br>&nbsp;&nbsp;&nbsp;&nbsp;add S to visited<br>&nbsp;&nbsp;&nbsp;&nbsp;while queue is not empty<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;dequeue curr from queue<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if curr == G return map<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;foreach of currs unvisited neighbours, n:<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;add n to visited<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;add curr as n's parent in parent map<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;enqueue n to top of stack<br>|
