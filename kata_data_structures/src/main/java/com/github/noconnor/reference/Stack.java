package com.github.noconnor.reference;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class Stack<E extends Comparable<? extends E>> {

    private E[] stack;
    private int top;

    public Stack(int size) {
        this.stack = (E[]) new Comparable[size];
        this.top = -1;
    }

    public void push(E element) {
        if (top == stack.length) {
            throw new IllegalStateException("Stack is full");
        }
        stack[++top] = element;
    }

    public E pop() {
        return top < 0 ? null : stack[top--];
    }

    public E peek() {
        return top >= 0 ? stack[top] : null;
    }

    public boolean isEmpty() {
        return top < 0;
    }

    public boolean isFull() {
        return top == stack.length - 1;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>(5);
        stack.push(7);
        stack.push(4);
        stack.push(5);
        stack.push(77);
        stack.push(4);

        assertThat(stack.isFull(), is(true));
        while (!stack.isEmpty()) {
            System.out.println("Peek:" + stack.peek());
            System.out.println("Pop:" + stack.pop());
        }

        assertThat(stack.isEmpty(), is(true));
        assertThat(stack.isFull(), is(false));
        stack.push(986);
        assertThat(stack.isFull(), is(false));
        assertThat(stack.isEmpty(), is(false));
    }
}
