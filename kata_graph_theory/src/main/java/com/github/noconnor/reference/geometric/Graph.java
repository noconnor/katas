package com.github.noconnor.reference.geometric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Graph {

    private HashMap<GraphNode, List<GraphEdge>> adjacencyList;

    private int numVertices;
    private int numEdges;

    public Graph() {
        this.adjacencyList = new HashMap<>();
        this.numEdges = 0;
        this.numVertices = 0;
    }

    public void addVertex(GraphNode node) {
        numVertices++;
        adjacencyList.put(node, new ArrayList<>());
    }

    public void addEdge(GraphEdge edge) {
        numEdges++;
        adjacencyList.get(edge.getStart()).add(edge);
    }

    public List<GraphEdge> getNeighbours(GraphNode node) {
        return new ArrayList<>(adjacencyList.get(node));
    }

    public Set<GraphNode> getVertices() {
        return adjacencyList.keySet();
    }

}
