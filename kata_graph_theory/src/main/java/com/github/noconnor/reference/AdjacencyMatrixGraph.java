package com.github.noconnor.reference;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class AdjacencyMatrixGraph {

  private int[][] matrix;

  private int numVertices;
  private int numEdges;

  public AdjacencyMatrixGraph() {
    this.matrix = new int[1][1];
    this.numVertices = 0;
    this.numEdges = 0;
  }

  public void addVertex() {
    numVertices++;
    int v = numVertices;
    if (v >= matrix.length) {
      int[][] newMatrix = new int[v * 2][v * 2];
      for (int i = 0; i < numVertices; i++) {
        for (int j = 0; j < numVertices; j++) {
          newMatrix[i][j] = matrix[i][j];
        }
      }
      matrix = newMatrix;
    }
    for (int i = 0; i < numVertices; i++) {
      matrix[v][i] = 0;
    }
  }

  public void addEdge(int start, int end) {
    numEdges++;
    matrix[start][end] += 1;
  }

  public List<Integer> getNeighbours(int v) {
    // find all non-zero matrix entries in row 'v'
    List<Integer> neighbours = new ArrayList<>();
    for (int i = 0; i < numVertices; i++) {
      for (int j = 0; j < matrix[v][i]; j++) {
        neighbours.add(i);
      }
    }
    return neighbours;
  }

  public List<Integer> getDistance2(int v) {
    // find all node reachable in exactly two hops
    List<Integer> twoHops = new ArrayList<>();
    for (int i = 0; i < numVertices; i++) {
      for (int j = 0; j < matrix[v][i]; j++) {
        twoHops.addAll(getNeighbours(i));
      }
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
    for (int i = 0; i < numVertices; i++) {
      for (int j = 0; j < numVertices; j++) {
        builder.append(matrix[i][j] + " ");
      }
      builder.append("\n");
    }
    return builder.toString();
  }

  public static void main(String[] args) {
    AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph();
    graph.addVertex();
    graph.addVertex();
    graph.addVertex();
    graph.addVertex();

    graph.addEdge(0, 1);
    graph.addEdge(0, 2);
    graph.addEdge(1, 3);
    graph.addEdge(2, 1);
    graph.addEdge(2, 3);

    System.out.println(graph);
    System.out.println("Neighbours of node 0: " + graph.getNeighbours(0));
    System.out.println("Neighbours of node 1: " + graph.getNeighbours(1));
    System.out.println("Neighbours of node 2: " + graph.getNeighbours(2));
    System.out.println("Neighbours of node 3: " + graph.getNeighbours(3));
    System.out.println("Two hop neighbours of node 0: " + graph.getDistance2(0));
    System.out.println("Two hop neighbours of node 1: " + graph.getDistance2(1));
    System.out.println("Two hop neighbours of node 2: " + graph.getDistance2(2));
    System.out.println("Two hop neighbours of node 3: " + graph.getDistance2(3));

    assertThat(graph.getNumEdges(), is(5));
    assertThat(graph.getNumVertices(), is(4));
    assertThat(graph.getNeighbours(0), is(newList(1, 2)));
    assertThat(graph.getNeighbours(1), is(newList(3)));
    assertThat(graph.getNeighbours(2), is(newList(1, 3)));
    assertThat(graph.getNeighbours(3), is(emptyList()));

    assertThat(graph.getDistance2(0), is(newList(3, 1, 3)));
    assertThat(graph.getDistance2(1), is(emptyList()));
    assertThat(graph.getDistance2(2), is(newList(3)));
    assertThat(graph.getDistance2(3), is(emptyList()));
  }

  private static List<Integer> newList(int... entries) {
    List<Integer> list = new ArrayList();
    for (int i : entries) {
      list.add(i);
    }
    return list;
  }

}
