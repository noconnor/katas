package com.github.noconnor.reference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class BreadthFirstSearch {

  public List<Integer> findPath(Graph graph, int start, int end) {

    Set<Integer> visited = new HashSet<>();
    Map<Integer, Integer> parentMap = new HashMap<>();
    Queue<Integer> toExplore = new LinkedBlockingQueue();

    toExplore.offer(start);
    visited.add(start);

    boolean found = false;

    while (!toExplore.isEmpty()) {
      int curr = toExplore.poll();
      if (curr == end) {
        found = true;
        break;
      }

      List<Integer> neighbours = graph.getNeighbours(curr);
      for (Integer neighbour : neighbours) {
        if (!visited.contains(neighbour)) {
          visited.add(neighbour);
          parentMap.put(neighbour, curr);
          toExplore.offer(neighbour);
        }
      }
    }

    if (!found) {
      System.out.print("No path from " + start + " to " + end);
      return new ArrayList<>();
    }

    LinkedList<Integer> path = new LinkedList<>();
    int curr = end;
    while (curr != start) {
      path.addFirst(curr);
      curr = parentMap.get(curr);
    }
    path.addFirst(start);

    return path;
  }

  public static void main(String[] args) {
    Graph graph = new AdjacencyListGraph();
    for (int i = 0; i < 6; i++) {
      graph.addVertex();
    }

    graph.addEdge(0, 1);
    graph.addEdge(0, 2);
    graph.addEdge(1, 5);
    graph.addEdge(1, 3);
    graph.addEdge(2, 3);
    graph.addEdge(2, 1);
    graph.addEdge(3, 4);
    graph.addEdge(4, 5);

    System.out.println(graph);

    BreadthFirstSearch bfs = new BreadthFirstSearch();
    List<Integer> path = bfs.findPath(graph, 0, 5);
    assertThat(path, containsInAnyOrder(0, 1, 5));

    System.out.println("Path from 0 to 5 = " + path);

    path = bfs.findPath(graph, 4, 0);
    assertThat(path, is(emptyList()));
  }
}
