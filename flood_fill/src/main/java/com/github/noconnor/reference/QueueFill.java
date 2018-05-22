package com.github.noconnor.reference;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

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

    canvas[n.x][n.y] = replaceColour;
    Queue<Node> nodes = new LinkedBlockingQueue<>();
    nodes.add(n);

    while (!nodes.isEmpty()) {

      Node node = nodes.poll();
      Node east = new Node(node.x - 1, node.y);
      Node west = new Node(node.x + 1, node.y);
      Node south = new Node(node.x, node.y - 1);
      Node north = new Node(node.x, node.y + 1);

      if (isInBounds(east) && canvas[east.x][east.y] == targetColour) {
        canvas[east.x][east.y] = replaceColour;
        nodes.add(east);
      }

      if (isInBounds(west) && canvas[west.x][west.y] == targetColour) {
        canvas[west.x][west.y] = replaceColour;
        nodes.add(west);
      }

      if (isInBounds(south) && canvas[south.x][south.y] == targetColour) {
        canvas[south.x][south.y] = replaceColour;
        nodes.add(south);
      }

      if (isInBounds(north) && canvas[north.x][north.y] == targetColour) {
        canvas[north.x][north.y] = replaceColour;
        nodes.add(north);
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
  }

}
