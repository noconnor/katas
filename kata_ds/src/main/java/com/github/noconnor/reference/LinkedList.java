package com.github.noconnor.reference;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class LinkedList<E> {

  static class Node<E> {
    E data;
    Node<E> next;
    Node<E> prev;
    Node(E data){
      this.data=data;
    }
    Node(E data, Node<E> neighbour){
      this.data=data;
      this.next=neighbour;
      this.prev=neighbour.prev;
      this.next.prev=this;
      this.prev.next=this;
    }
  }
  // sentinels
  private Node<E> head;
  private Node<E> tail;

  private int size;

  public LinkedList(){
    this.head=new Node<>(null);
    this.tail=new Node<>(null);
    this.head.next=tail;
    this.tail.prev=head;
  }

  public E get(int index){
    isValidIndex(index);
    Node<E> curr = head.next;
    for(int i=0; i<index && curr.next!=null; i++){
      curr=curr.next;
    }
    return curr.data;
  }

  public void add(E data){
    isValidData(data);
    size++;
    // new data is added to the tail
    new Node<>(data, tail);
  }

  public void add(int index, E data){
    isValidData(data);
    Node<E> curr = head.next;
    for(int i=0; i<index && curr.next!=null; i++){
      curr=curr.next;
    }
    size++;
    new Node<>(data, curr);
  }

  public void set(int index, E data){
    isValidIndex(index);
    isValidData(data);
    Node<E> curr = head.next;
    for(int i=0; i<index && curr.next!=null; i++){
      curr=curr.next;
    }
    curr.data=data;
  }

  public E remove(int index){
    isValidIndex(index);
    Node<E> curr = head.next;
    for(int i=0; i<index && curr.next!=null; i++){
      curr=curr.next;
    }
    curr.prev.next=curr.next;
    if(curr.next!=null){
      curr.next.prev = curr.prev;
    }
    size--;
    return curr.data;
  }

  public E remove(E data){
    isValidData(data);
    Node<E> curr= head.next;
    for(int i=0; i<size && curr.next!=null; i++){
      if(data.equals(curr.data)){
        curr.next.prev=curr.prev;
        curr.prev.next=curr.next;
        size--;
        return curr.data;
      }
      curr=curr.next;
    }
    return null;
  }

  public int size(){ return size; }

  public String toString(){
    StringBuilder sb = new StringBuilder();
    String prefix="";
    Node<E> curr = head.next;
    while(curr.next!=null){
      sb.append(prefix);
      sb.append(curr.data);
      prefix="->";
      curr=curr.next;
    }
    return sb.toString();
  }

  private void isValidData(E data){
    if(data==null) throw new NullPointerException("Invalid data");
  }

  private void isValidIndex(int index){
    if(index<0 || index>=size) throw new IndexOutOfBoundsException("Invalid index");
  }

  public static void main(String[] args) {
    LinkedList<Integer> list = new LinkedList<>();
    list.add(1);
    list.add(3);
    list.add(55);
    list.add(76);
    list.add(3);

    System.out.println(list);
    list.remove(new Integer(55));
    System.out.println(list);
    list.set(3, 99);
    System.out.println(list);
    list.set(0, 567);
    System.out.println(list);
    list.set(list.size() - 1, 67);
    System.out.println(list);
    list.add(33);
    System.out.println(list);
    list.remove(2);
    System.out.println(list);
    list.add(2, 678);
    System.out.println(list);

    assertThat(list.get(0), is(567));
    assertThat(list.get(list.size() - 1), is(33));
  }

}
