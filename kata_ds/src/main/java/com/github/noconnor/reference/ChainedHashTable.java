package com.github.noconnor.reference;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;

public class ChainedHashTable<K, V> {

  // singly linked
  static class Node {
    Object key;
    Object value;
    Node next;

    Node(Object k, Object v, Node next) {
      this.key = k;
      this.value = v;
      this.next = next;
    }
  }

  private Node[] table;
  private int capacity;
  private int n;

  public ChainedHashTable(int size) {
    if (size <= 0) throw new IllegalStateException("capacity must be greater than 0");
    this.table = new Node[size];
    this.capacity = size;
  }

  public V get(K key) {
    validateKey(key);

    int index = calculateIndex(key);
    for (Node x = table[index]; x != null; x = x.next) {
      if (key.equals(x.key)) {
        return (V) x.value;
      }
    }

    return null;
  }

  public void put(K key, V value) {
    validateKey(key);
    int index = calculateIndex(key);

    for (Node x = table[index]; x != null; x = x.next) {
      if (key.equals(x.key)) {
        x.value = value;
        return;
      }
    }
    n++;
    table[index] = new Node(key, value, table[index]);
  }

  public void remove(K key) {
    validateKey(key);

    int index = calculateIndex(key);
    Node previous = null;
    for (Node x = table[index]; x != null; x = x.next) {
      if (key.equals(x.key)) {
        if (previous != null) {
          previous.next = x.next;
        } else {
          table[index] = x.next;
        }
        n--;
        return;
      }
      previous = x;
    }
  }

  public String toString(){
    StringBuilder sb = new StringBuilder("\n");
    for(int i=0; i<table.length; i++){
      if(table[i]!=null){
        Node curr=table[i];
        String prefix="";
        do{
          sb.append(prefix);
          sb.append("[");
          sb.append(curr.key);
          sb.append(":");
          sb.append(curr.value);
          sb.append("]");
          prefix="->";
          curr=curr.next;
        } while(curr!=null);
        sb.append("\n");
      }
    }
    return sb.toString();
  }

  public int size() {
    return n;
  }

  private int calculateIndex(K key) {
    return (key.hashCode() & 0x7fffffff) % capacity;
  }

  private void validateKey(K key) {
    if (key == null) throw new IllegalArgumentException("Key cannot be null");
  }

  public static void main(String[] args) {
    ChainedHashTable<String, String> table = new ChainedHashTable<>(16);
    table.put("AaAaAa", "Value1");
    table.put("AaAaBB", "Value2");
    table.put("AaBBAa", "Value3");
    table.put("Test",   "Value4");
    System.out.println(table);

    assertThat(table.get("AaAaAa"), is("Value1"));
    assertThat(table.get("AaAaBB"), is("Value2"));
    assertThat(table.get("AaBBAa"), is("Value3"));
    assertThat(table.get("Test"), is("Value4"));
    assertThat(table.size(), is(4));

    table.remove("AaBBAa");
    assertThat(table.get("AaAaAa"), is("Value1"));
    assertThat(table.get("AaAaBB"), is("Value2"));
    assertThat(table.get("AaBBAa"), nullValue());
    assertThat(table.get("Test"), is("Value4"));
    assertThat(table.size(), is(3));

    table.remove("AaAaAa");
    assertThat(table.get("AaAaAa"), nullValue());
    assertThat(table.get("AaAaBB"), is("Value2"));
    assertThat(table.get("AaBBAa"), nullValue());
    assertThat(table.get("Test"), is("Value4"));
    assertThat(table.size(), is(2));

    System.out.println("Value of key \"Test\": " + table.get("Test") + " [size: " + table.size() + "]");
    System.out.println("Value of key \"AaAaBB\" " + table.get("AaAaBB") + " [size:" + table.size() + "]");
    System.out.println("Value of key \"AaAaAa\" " + table.get("AaAaAa") + " [size:" + table.size() + "]");
    System.out.println("Value of key \"AaBBAa\" " + table.get("AaBBAa") + " [size:" + table.size() + "]");
  }

}
