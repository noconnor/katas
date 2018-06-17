package com.github.noconnor.reference.trie;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class StringTrie {

  private TrieNode root;

  public StringTrie() {
    this.root = new TrieNode(null);
  }

  public void add(String word) {
    validateWord(word);
    TrieNode curr = root;
    for (Character c : word.toCharArray()) {
      TrieNode child = curr.getChildren().get(c);
      if (child == null) {
        child = new TrieNode(curr);
        curr.getChildren().put(c, child);
      }
      curr = child;
    }
    curr.setText(word);
  }

  public boolean contains(String word) {
    validateWord(word);
    TrieNode curr = root;
    for (Character c : word.toCharArray()) {
      curr = curr.getChildren().get(c);
      if (curr == null) {
        return false;
      }
    }
    return curr.isWord();
  }

  public void remove(String word) {
    validateWord(word);
    remove(root, word, 0);
  }

  private boolean remove(TrieNode node, String word, int index) {
    if(index == word.length()) {
      if(!node.isWord()) {
        return false;
      }
      node.setText(null);
      return node.getChildren().isEmpty();
    }
    TrieNode child = node.getChildren().get(word.charAt(index));
    if(child!=null && remove(child, word, index+1)){
      node.getChildren().remove(word.charAt(index));
      return !node.isWord() && node.getChildren().isEmpty();
    }

    return false;
  }

  public String toString() {
    StringBuilder builder = new StringBuilder("\n");
    preOrder(root, builder, 0);
    return builder.toString();
  }

  private void preOrder(TrieNode node, StringBuilder builder, int index) {
    if (node != null) {
      if (index == 0) {
        builder.append("(");
        builder.append(node);
        builder.append(")\n");
      } else {
        for (int i = 0; i < index; i++) {
          builder.append("|\t");
        }
        builder.append("|-(");
        builder.append(node);
        builder.append(")\n");
      }

      int nextLevel = index + 1;
      for (TrieNode child : node.getChildren().values()) {
        preOrder(child, builder, nextLevel);
      }
    }
  }

  private void validateWord(String word) {
    if (word == null) throw new NullPointerException("word cannot be null");
  }

  public static void main(String[] args) {
    StringTrie trie = new StringTrie();

    trie.add("a");
    trie.add("a");
    trie.add("ate");
    trie.add("ate");
    trie.add("ear");
    trie.add("east");
    trie.add("eats");
    trie.add("eat");
    System.out.println(trie);
    trie.remove("ear");
    System.out.println(trie);

    assertThat(trie.contains("easter"), is(false));
    assertThat(trie.contains("east"), is(true));
    assertThat(trie.contains("a"), is(true));
    assertThat(trie.contains("ears"), is(false));
  }

}
