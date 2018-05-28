package com.github.noconnor.reference.geometric;

import static java.util.Collections.emptyList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

import org.hamcrest.Matchers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class AStarSearch {

    private int nodesChecked;

    public List<GraphNode> findPath(Graph graph, GraphNode start, GraphNode end) {
        nodesChecked = 0;
        // Initialise
        Set<GraphNode> visited = new HashSet<>();
        Map<GraphNode, GraphNode> parentMap = new HashMap<>();
        Map<GraphNode, Float> weighting = new HashMap<>();
        Map<GraphNode, Float> distanceFromStart = new HashMap<>();
        PriorityQueue<GraphNode> pq = new PriorityQueue<>((o1, o2) -> {
            return Float.compare(weighting.get(o1), weighting.get(o2));
        });

        for (GraphNode node : graph.getVertices()) {
            weighting.put(node, Float.MAX_VALUE);
        }

        pq.offer(start);
        distanceFromStart.put(start, 0F);
        weighting.put(start, (float)start.estimateDistance(end));

        boolean found = false;

        while (!pq.isEmpty()) {
            GraphNode curr = pq.poll();
            if (!visited.contains(curr)) {
                nodesChecked++;
                visited.add(curr);
                if (curr.equals(end)) {
                    found = true;
                    break;
                }
                float baseDistance = distanceFromStart.get(curr);
                System.out.println("Testing route via: " + curr);
                for (GraphEdge neighbour : graph.getNeighbours(curr)) {
                    GraphNode next = neighbour.getEnd();
                    float distance = baseDistance + neighbour.getDistance();
                    float weight = distance + (float) next.estimateDistance(end);
                    if (weight < weighting.get(next)) {
                        // Set weighting & distance before updating PQ
                        weighting.put(next, weight);
                        distanceFromStart.put(next, distance);
                        pq.offer(next);
                        parentMap.put(next, curr);
                    }
                }
            }
        }

        System.out.println("Weighting " + weighting);

        if (!found) {
            System.out.println("No path");
            return new LinkedList<>();
        }

        LinkedList<GraphNode> path = new LinkedList<>();
        GraphNode curr = end;
        while (!curr.equals(start)) {
            path.addFirst(curr);
            curr = parentMap.get(curr);
        }
        path.addFirst(start);

        return path;
    }

    public static void main(String[] args) {
        Graph graph = new Graph();

        // see src/main/resources/geo_search_example.png
        GraphNode sanDiego = new GraphNode("San Diego", 32.7157f, -117.161f);
        GraphNode seattle = new GraphNode("Seattle", 47.6062f, -122.3321f);
        GraphNode sacramento = new GraphNode("Sacramento", 38.5816f, -121.4944f);
        GraphNode sanJose = new GraphNode("San Jose", 37.3382f, -121.8863f);
        GraphNode tuscon = new GraphNode("Tuscon", 32.2226f, -110.9747f);
        GraphNode denver = new GraphNode("Denver", 39.7392f, -104.9903f);
        GraphNode dublin = new GraphNode("Dublin", 53.3498f, -6.2603f);

        graph.addVertex(sanDiego);
        graph.addVertex(seattle);
        graph.addVertex(sacramento);
        graph.addVertex(sanJose);
        graph.addVertex(tuscon);
        graph.addVertex(denver);

        graph.addEdge(new GraphEdge("Edge1", sanDiego, sanJose));
        graph.addEdge(new GraphEdge("Edge2", sanJose, sacramento));
        graph.addEdge(new GraphEdge("Edge3", sacramento, seattle));
        graph.addEdge(new GraphEdge("Edge4", sanDiego, denver));
        graph.addEdge(new GraphEdge("Edge5", denver, seattle));
        graph.addEdge(new GraphEdge("Edge6", sanDiego, tuscon));

        AStarSearch as = new AStarSearch();
        List<GraphNode> path = as.findPath(graph, sanDiego, seattle);
        System.out.println(path);
        assertThat(as.nodesChecked, is(4));

        assertThat(path, containsInAnyOrder(sanDiego, sanJose, sacramento, seattle));

        List<GraphNode> noPath = as.findPath(graph, sanDiego, dublin);
        System.out.println(noPath);
        assertThat(noPath, is(emptyList()));
        assertThat(as.nodesChecked, is(6));

    }

}
