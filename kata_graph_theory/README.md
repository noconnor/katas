## Objective 

1. [Implement a Graph using an Adjacency Matrix](src/main/java/com/github/noconnor/reference/AdjacencyMatrixGraph.java)
2. [Implement a Graph using an Adjacency List](src/main/java/com/github/noconnor/reference/AdjacencyListGraph.java)
3. [Implement a DFS algorithm](src/main/java/com/github/noconnor/reference/DepthFirstSearch.java)
4. [Implement a BFS algorithm](src/main/java/com/github/noconnor/reference/BreadthFirstSearch.java)
5. [Implement the Dijkstra search algorithm](src/main/java/com/github/noconnor/reference/geometric/DijkstraSearch.java)
6. [Implement the A* search algorithm](src/main/java/com/github/noconnor/reference/geometric/AStarSearch.java)


## Reference:

Coursera:

* [Adjacency Matrix/List](https://www.coursera.org/learn/advanced-data-structures/supplement/EL8R1/week-1-additional-resources)
* [DFS and BFS](https://www.coursera.org/learn/advanced-data-structures/supplement/4P3oy/week-2-additional-resources)

<br/>

## Algorithms

| DFS (Depth First Search) | BFS (Breadth First Search) |
|:----- |:----- |
|<br>DFS(S, G)<br>&nbsp;&nbsp;&nbsp;&nbsp;initialise **stack**, hashset, map<br>&nbsp;&nbsp;&nbsp;&nbsp;push S to stack<br>&nbsp;&nbsp;&nbsp;&nbsp;add S to visited<br>&nbsp;&nbsp;&nbsp;&nbsp;while stack is not empty<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;pop curr from stack<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if curr == G return map<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;foreach of currs unvisited neighbours, n:<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;add n to visited<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;add curr as n's parent in parent map<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;push n to top of stack<br>| <br>BFS(S, G)<br>&nbsp;&nbsp;&nbsp;&nbsp;initialise **queue**, hashset, map<br>&nbsp;&nbsp;&nbsp;&nbsp;enqueue S to queue<br>&nbsp;&nbsp;&nbsp;&nbsp;add S to visited<br>&nbsp;&nbsp;&nbsp;&nbsp;while queue is not empty<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;dequeue curr from queue<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if curr == G return map<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;foreach of currs unvisited neighbours, n:<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;add n to visited<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;add curr as n's parent in parent map<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;enqueue n to top of stack<br>|

Visualisation of test grid used in DFS and BFS code:

![Test Grid](src/main/resources/test_graph.png?raw=true)

<br/>
<br/>

| Dijkstra (BFS with weighting) | A* |
|:----- |:----- |
|<br>Dijkstra(S, G):<br>&nbsp;&nbsp;Initialise: Priority Queue (PQ), visited HashSet, parentMap HashMap, distances to Infinity<br>&nbsp;&nbsp;Enqueue {S, 0} on to PQ<br>&nbsp;&nbsp;while PQ is not empty:<br>&nbsp;&nbsp;&nbsp;&nbsp;dequeue node curr from front of queue<br>&nbsp;&nbsp;&nbsp;&nbsp;if curr is not visited:<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;add curr to visited set<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if curr == G return parentMap<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;foreach of curr's neighbours ,n, not in visited set<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if path through curr to n is shorter:<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;update n's distance<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;update curr as n's parent in parentMap<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;enqueue {n, distance} into PQ|<br>AStar(S, G):<br>&nbsp;&nbsp;Initialise: Priority Queue (PQ), visited HashSet, parentMap HashMap, distances to Infinity<br>&nbsp;&nbsp;Enqueue {S, 0} on to PQ<br>&nbsp;&nbsp;while PQ is not empty:<br>&nbsp;&nbsp;&nbsp;&nbsp;dequeue node curr from front of queue<br>&nbsp;&nbsp;&nbsp;&nbsp;if curr is not visited:<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;add curr to visited set<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if curr == G return parentMap<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;foreach of curr's neighbours ,n, not in visited set<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if path through curr to n is shorter:<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;update n's distance **+ calculate distance to target**<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;update curr as n's parent in parentMap<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;enqueue {n, distance + **distance to target estimate**} into PQ|
| Running time: ```O(E*log(E)+ V)``` | Running time: ```O(E*log(E)+ V)``` |

Visualisation of test grid used in Dijkstra and A* code:

![Test Grid](src/main/resources/geo_search_example.png?raw=true)
<br/>
