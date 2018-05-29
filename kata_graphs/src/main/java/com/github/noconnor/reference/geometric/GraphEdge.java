package com.github.noconnor.reference.geometric;

public class GraphEdge {

    String label;
    GraphNode start;
    GraphNode end;

    float distance;

    public GraphEdge(String label, GraphNode start, GraphNode end) {
        this.label = label;
        this.start = start;
        this.end = end;
        this.distance = (int) start.estimateDistance(end);
        System.out.println(String.format("Distance from %s to %s is %f", start.label, end.label, distance));
    }

    public GraphNode getStart() {
        return start;
    }

    public GraphNode getEnd() {
        return end;
    }

    public float getDistance() {
        return distance;
    }

    public int hashCode() {
        return label.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof GraphEdge) {
            return label.equals(((GraphEdge) obj).label);
        }
        return false;
    }

    public String toString() {
        return label;
    }
}
