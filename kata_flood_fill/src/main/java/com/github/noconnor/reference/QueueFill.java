package com.github.noconnor.reference;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class QueueFill {

    private int[][] canvas;

    private QueueFill(int[][] canvas) {
        this.canvas = canvas;
    }

    private void fill(Node n, int targetColour, int replaceColour) {

        if (targetColour == replaceColour) {
            return;
        }

        if (n.x < 0 || n.x > canvas.length - 1 || n.y < 0 || n.y > canvas[n.x].length - 1) {
            return;
        }

        if (canvas[n.x][n.y] != targetColour) {
            return;
        }

        Queue<Node> nodes = new LinkedBlockingQueue<>();
        nodes.add(n);

        while (!nodes.isEmpty()) {

            Node node = nodes.poll();

            if (isInBounds(node) && canvas[node.x][node.y] == targetColour) {

                nodes.add(new Node(node.x - 1, node.y));
                nodes.add(new Node(node.x + 1, node.y));
                nodes.add(new Node(node.x, node.y - 1));
                nodes.add(new Node(node.x, node.y + 1));

                canvas[node.x][node.y] = replaceColour;

                System.out.println("Processing: " + node);
            }
        }
    }

    private boolean isInBounds(Node n) {
        return n.x >= 0 && n.x < canvas.length && n.y >= 0 && n.y < canvas[n.x].length;
    }


    private static class Node {

        private int x;
        private int y;

        private Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "[" + x + ", " + y + "]";
        }
    }

    public static void main(String[] args) {
        int[][] canvas = new int[][]{
            new int[]{0, 1, 0, 0, 1},
            new int[]{0, 1, 0, 1, 0},
            new int[]{1, 0, 0, 1, 0},
            new int[]{0, 0, 1, 0, 0},
            new int[]{1, 1, 0, 0, 0},
            };

        int[][] expected = new int[][]{
            new int[]{0, 1, 1, 1, 1},
            new int[]{0, 1, 1, 1, 0},
            new int[]{1, 1, 1, 1, 0},
            new int[]{1, 1, 1, 0, 0},
            new int[]{1, 1, 0, 0, 0},
            };

        System.out.println("Before:");
        print(canvas);
        new QueueFill(canvas).fill(new Node(3, 0), 0, 1);

        System.out.println("\nAfter:");
        print(canvas);

        assertThat(canvas, is(equalTo(expected)));
    }

    private static void print(int[][] array) {
        for (int[] x : array) {
            for (int y : x) {
                System.out.print(y + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
