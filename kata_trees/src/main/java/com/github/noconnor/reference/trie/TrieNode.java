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

    public TrieNode getParent(){
        return parent;
    }

    public void addWord(String word) {
        addWord(word, 0);
    }

    private void addWord(String word, int index) {

        String substring = word.substring(index);

        if(substring.length() > 1) {
            char firstCharacter = word.charAt(index);
            TrieNode node = children.get(firstCharacter);
            if (node == null) {
                node = new TrieNode(this);
            }
            text = substring;
            children.put(firstCharacter, node);
            node.addWord(word, ++index);
        } else {
            text = word;
            isWord = true;
        }
    }

    public String toString(){
        return "[isWord:" + isWord +"][text:" + text + "]" + children;
    }

}
