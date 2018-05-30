package com.github.noconnor.reference.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {

  private TrieNode parent;

  private boolean isWord;
  private String text;
  private HashMap<Character, TrieNode> children;


  public TrieNode(TrieNode parent) {
    this.parent = parent;
    this.children = new HashMap<>();
  }

  public boolean isWord() {
    return isWord;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
    this.isWord = true;
  }

  public Map<Character, TrieNode> getChildren() {
    return children;
  }

  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append(children.keySet().toString());
    if (isWord) {
      builder.append("[word:");
      builder.append(text);
      builder.append("]");
    }
    return builder.toString();
  }

}
