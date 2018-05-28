package com.github.noconnor.reference.geometric;

public class GraphNode {

    String label;
    float latitude;
    float longitude;

    public GraphNode(String label, float latitude, float longitude) {
        this.label = label;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double estimateDistance(GraphNode n) {
        // Ignore curvature of the earth, calculate straight line distance
        double x = (latitude - n.latitude) * (latitude - n.latitude);
        double y = (longitude - n.longitude) * (longitude - n.longitude);
        return Math.sqrt(x + y);
    }

    @Override
    public String toString() {
        return label;
    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GraphNode) {
            return label.equals(((GraphNode) obj).label);
        }
        return false;
    }
}
