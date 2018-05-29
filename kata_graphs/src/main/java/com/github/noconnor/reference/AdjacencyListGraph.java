package com.github.noconnor.reference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

public class AdjacencyListGraph implements Graph {

  private Map<Integer, List<Integer>> graph;

  private int numVertices;
  private int numEdges;

  public AdjacencyListGraph() {
    this.graph = new HashMap<>();
  }

  public void addVertex() {
    graph.put(numVertices, new ArrayList<>());
    numVertices++;
  }

  public void addEdge(int start, int end) {
    numEdges++;
    graph.get(start).add(end);
  }

  public List<Integer> getNeighbours(int v) {
    return new ArrayList<>(graph.get(v));
  }

  public List<Integer> getDistance2(int v) {
    List<Integer> twoHops = new ArrayList<>();
    for (Integer neighbour : getNeighbours(v)) {
      twoHops.addAll(getNeighbours(neighbour));
    }
    return twoHops;
  }

  public int getNumEdges() {
    return numEdges;
  }

  public int getNumVertices() {
    return numVertices;
  }

  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (Integer v : graph.keySet()) {
      List<Integer> edges = graph.get(v);
      for (int i = 0; i < numVertices; i++) {
        int value = edges.contains(i) ? 1 : 0;
        builder.append(value + "");
      }
      builder.append("\n");
    }
    return builder.toString();
  }

  public static void main(String[] args) {
    // See src/main/resources/test_graph.png
    AdjacencyListGraph graph = new AdjacencyListGraph();
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
    System.out.println("Neighbours of node 0: " + graph.getNeighbours(0));
    System.out.println("Neighbours of node 1: " + graph.getNeighbours(1));
    System.out.println("Neighbours of node 2: " + graph.getNeighbours(2));
    System.out.println("Neighbours of node 3: " + graph.getNeighbours(3));
    System.out.println("Two hop neighbours of node 0: " + graph.getDistance2(0));
    System.out.println("Two hop neighbours of node 1: " + graph.getDistance2(1));
    System.out.println("Two hop neighbours of node 2: " + graph.getDistance2(2));
    System.out.println("Two hop neighbours of node 3: " + graph.getDistance2(3));

    assertThat(graph.getNumEdges(), is(8));
    assertThat(graph.getNumVertices(), is(6));
    assertThat(graph.getNeighbours(0), containsInAnyOrder(1, 2));
    assertThat(graph.getNeighbours(1), containsInAnyOrder(5, 3));
    assertThat(graph.getNeighbours(2), containsInAnyOrder(3, 1));
    assertThat(graph.getNeighbours(3), containsInAnyOrder(4));

    assertThat(graph.getDistance2(0), containsInAnyOrder(5, 3, 1, 3));
    assertThat(graph.getDistance2(1), containsInAnyOrder(4));
    assertThat(graph.getDistance2(2), containsInAnyOrder(4, 5, 3));
    assertThat(graph.getDistance2(3), containsInAnyOrder(5));
  }

}
