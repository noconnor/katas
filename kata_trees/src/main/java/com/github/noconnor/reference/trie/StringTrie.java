package com.github.noconnor.reference.trie;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class StringTrie {

  private TrieNode root;

  public StringTrie() {
    this.root = new TrieNode(null);
  }

  public void insert(String word) {
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

  public boolean find(String wordToFind) {
    TrieNode curr = root;
    for (Character c : wordToFind.toCharArray()) {
      curr = curr.getChildren().get(c);
      if (curr == null) {
        return false;
      }
    }
    return curr.isWord();
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


  public static void main(String[] args) {
    StringTrie trie = new StringTrie();

    trie.insert("a");
    trie.insert("a");
    trie.insert("ate");
    trie.insert("ate");
    trie.insert("ear");
    trie.insert("east");
    trie.insert("eats");
    trie.insert("eat");
    System.out.println(trie);

    assertThat(trie.find("easter"), is(false));
    assertThat(trie.find("east"), is(true));
    assertThat(trie.find("a"), is(true));
    assertThat(trie.find("ears"), is(false));
  }

}
