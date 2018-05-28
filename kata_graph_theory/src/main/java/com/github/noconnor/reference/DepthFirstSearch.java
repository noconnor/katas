package com.github.noconnor.reference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class DepthFirstSearch {

  public List<Integer> findPath(Graph graph, int startNode, int endNode) {

    Set<Integer> visited = new HashSet<>();
    Map<Integer, Integer> parentMap = new HashMap<>();
    Stack<Integer> stack = new Stack<>();

    stack.push(startNode);
    visited.add(startNode);

    boolean found = false;

    while (!stack.isEmpty()) {

      int curr = stack.pop();
      if (curr == endNode) {
        found = true;
        break;
      }
      List<Integer> neighbours = graph.getNeighbours(curr);
      for (int neighbour : neighbours) {
        if (!visited.contains(neighbour)) {
          stack.push(neighbour);
          visited.add(neighbour);
          parentMap.put(neighbour, curr);
        }
      }
    }

    if (!found) {
      System.out.println("No path from " + startNode + " to " + endNode);
      return new ArrayList<>();
    }

    LinkedList<Integer> path = new LinkedList<>();
    int curr = endNode;
    while (curr != startNode) {
      path.addFirst(curr);
      curr = parentMap.get(curr);
    }
    path.addFirst(startNode);
    return path;
  }

  public static void main(String[] args) {
    // See src/main/resources/test_graph.png
    Graph graph = new AdjacencyMatrixGraph();
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

    DepthFirstSearch dfs = new DepthFirstSearch();
    List<Integer> path = dfs.findPath(graph, 0, 5);
    assertThat(path, containsInAnyOrder(0, 2, 3, 4, 5));

    System.out.println("Path from 0 to 5 = " + path);

    path = dfs.findPath(graph, 4, 0);
    assertThat(path, is(emptyList()));
  }

}
