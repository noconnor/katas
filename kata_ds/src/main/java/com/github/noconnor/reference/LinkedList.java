package com.github.noconnor.reference;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class LinkedList<E extends Comparable<? super E>> {

  // recursive data type
  private static class ListNode<E> {
    ListNode<E> prev;
    ListNode<E> next;
    E data;

    public ListNode(E data) {
      this.data = data;
    }

    public ListNode(E data, ListNode<E> prev) {
      this(data);
      this.next = prev.next;
      prev.next = this;
    }
  }

  private ListNode<E> head;
  private ListNode<E> tail;
  private int size = 0;

  public LinkedList() {
    head = new ListNode<>(null);
    tail = new ListNode<>(null);
    head.next = tail;
    tail.prev = head;
  }

  public int size() {
    return size;
  }

  public E get(int index) {
    if (index < 0 || index >= size) {
      throw new IllegalArgumentException("index must be valid");
    }
    ListNode<E> curr = head.next; // skip sentinel
    int count = 0;
    while (curr != null) {
      if (count == index) {
        return curr.data;
      }
      curr = curr.next;
      count++;
    }
    return null;
  }

  public void set(int index, E data) {
    if (index < 0 || index > size) {
      throw new IllegalArgumentException("Index is invalid");
    }

    if (data == null) {
      throw new NullPointerException("Data cannot be null");
    }

    ListNode<E> curr = head.next; // skip sentinel
    int i = 0;
    while (curr != null) {
      if (i == index) {
        ListNode<E> node = new ListNode<>(data);
        // order important
        node.next = curr;
        node.prev = curr.prev;
        node.next.prev = node;
        node.prev.next = node;

        size++;
        break;
      }
      curr = curr.next;
      i++;
    }
  }

  public void add(E data) {
    if (data == null) throw new NullPointerException("Data cannot be null");

    ListNode<E> node = new ListNode<>(data);

    node.next = head.next;
    node.prev = head;
    node.next.prev = node;
    node.prev.next = node;

    size++;
  }

  public void remove(E data) {
    if (data == null) throw new NullPointerException("Data cannot be null");

    ListNode curr = head.next; // skip sentinel
    while (curr != null) {
      if (data.equals(curr.data)) {
        curr.prev.next = curr.next;
        if (curr.next != null) {
          curr.next.prev = curr.prev;
        }
        size--;
        break;
      }
      curr = curr.next;
    }
  }


  public String toString() {
    StringBuilder builder = new StringBuilder();
    ListNode<E> curr = head.next;
    String prefix = "";
    while (curr != tail) {
      builder.append(prefix);
      builder.append(curr.data);
      prefix = ",";
      curr = curr.next;
    }
    return builder.toString();
  }

  public static void main(String[] args) {
    LinkedList<Integer> list = new LinkedList<>();
    list.add(1);
    list.add(3);
    list.add(55);
    list.add(76);
    list.add(3);

    System.out.println(list);
    list.remove(55);
    System.out.println(list);
    list.set(3, 99);
    System.out.println(list);
    list.set(0, 567);
    System.out.println(list);
    list.set(list.size(), 67);
    System.out.println(list);

    assertThat(list.get(0), is(567));
    assertThat(list.get(list.size() - 1), is(67));
    assertThat(list.get(3), is(3));
  }


}
