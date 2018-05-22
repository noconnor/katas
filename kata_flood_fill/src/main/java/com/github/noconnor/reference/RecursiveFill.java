package com.github.noconnor.reference;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class RecursiveFill {

  private int[][] canvas;

  private RecursiveFill(int[][] canvas) {
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

    fill(new Node(n.x + 1, n.y), targetColour, replaceColour);
    fill(new Node(n.x - 1, n.y), targetColour, replaceColour);
    fill(new Node(n.x, n.y + 1), targetColour, replaceColour);
    fill(new Node(n.x, n.y - 1), targetColour, replaceColour);

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
      new int[]{0, 0, 0, 0, 0, 1, 0, 1, 0, 1},
      new int[]{1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
      new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
      new int[]{0, 1, 0, 0, 0, 1, 1, 1, 1, 1},
      new int[]{1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
      new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    };

    int[][] expected = new int[][]{
      new int[]{0, 0, 0, 0, 0, 1, 1, 1, 1, 1},
      new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
      new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
      new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
      new int[]{1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
      new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    };

    System.out.println("Before:");
    print(canvas);
    new RecursiveFill(canvas).fill(new Node(2, 2), 0, 1);

    System.out.println("\nAfter:");
    print(canvas);

    // verify
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
