package com.github.noconnor.reference;

import java.util.List;

public interface Graph {

  void addVertex();

  void addEdge(int start, int end);

  List<Integer> getNeighbours(int v);

  List<Integer> getDistance2(int v);

  int getNumVertices();

  int getNumEdges();
}
