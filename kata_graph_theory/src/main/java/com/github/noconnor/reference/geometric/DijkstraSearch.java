package com.github.noconnor.reference.geometric;

import static java.util.Collections.emptyList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class DijkstraSearch {

    public List<GraphNode> findPath(Graph graph, GraphNode start, GraphNode end) {

        // Initialise
        Map<GraphNode, GraphNode> parentMap = new HashMap<>();
        Map<GraphNode, Float> distanceFromStartNode = new HashMap<>();
        PriorityQueue<GraphNode> pq = new PriorityQueue<>((o1, o2) -> {
            return Float.compare(distanceFromStartNode.get(o1), distanceFromStartNode.get(o2));
        });
        Set<GraphNode> visited = new HashSet<>();

        // Running time: O(|V|)
        for (GraphNode vertex : graph.getVertices()) {
            distanceFromStartNode.put(vertex, Float.MAX_VALUE);
        }

        // Find path
        pq.offer(start);
        distanceFromStartNode.put(start, 0.0F);
        boolean found = false;
        while (!pq.isEmpty()) {
            GraphNode curr = pq.poll();
            if (!visited.contains(curr)) {
                visited.add(curr);
                if (curr.equals(end)) {
                    found = true;
                    break;
                }

                float distance = distanceFromStartNode.get(curr);
                for (GraphEdge neighbour : graph.getNeighbours(curr)) {
                    GraphNode nextNode = neighbour.getEnd();
                    if (!visited.contains(neighbour.getEnd())) {
                        System.out.println("Testing route via: " + nextNode);
                        // find next shortest hop
                        float distanceToNeighbour = distance + neighbour.getDistance();
                        float existingMappedDistance = distanceFromStartNode.get(nextNode);

                        if (distanceToNeighbour < existingMappedDistance) {
                            distanceFromStartNode.put(nextNode, distanceToNeighbour);
                            pq.offer(nextNode);
                            parentMap.put(nextNode, curr);
                        }
                    }
                }
            }
        }

        if (!found) {
            System.out.println("no path found");
            return new LinkedList<>();
        }

        LinkedList<GraphNode> path = new LinkedList<>();
        GraphNode curr = end;
        while (curr != start) {
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
        GraphNode tuscon = new GraphNode("Tuscon", 32.7157f, -117.1611f);
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

        DijkstraSearch ds = new DijkstraSearch();
        List<GraphNode> path = ds.findPath(graph, sanDiego, seattle);
        System.out.println(path);

        assertThat(path, containsInAnyOrder(sanDiego, sanJose, sacramento, seattle));

        List<GraphNode> noPath = ds.findPath(graph, sanDiego, dublin);
        System.out.println(noPath);
        assertThat(noPath, is(emptyList()));
    }

}
