package com.github.noconnor.reference.trie;

import java.util.HashMap;

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

    public TrieNode getParent() {
        return parent;
    }

    public void addWord(String word) {
        addWord(word, word);
    }

    private void addWord(String stem, String word) {

        if (stem.length() > 1) {
            char firstCharacter = stem.charAt(0);
            TrieNode node = children.get(firstCharacter);
            if (node == null) {
                node = new TrieNode(this);
                node.text = stem;
            }
            node.addWord(stem.substring(1), word);
            children.put(firstCharacter, node);
        } else {
            text = word;
            isWord = true;
        }
    }

    public String toString() {
        return "[isWord:" + isWord + "][text:" + text + "]";
    }

}
