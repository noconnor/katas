package com.github.noconnor.reference.trie;

public class StringTrie {

    private TrieNode root;

    public void insert(String word){
        if(root == null){
            root = new TrieNode(null);
        }
        root.addWord(word);
    }

    public boolean find(String wordToFind){
        return false;
    }


    public String toString(){
        return root.toString();
    }


    public static void main(String[] args){
        StringTrie trie = new StringTrie();

        trie.insert("hello");
        System.out.println(trie);

        trie.insert("hell");
        System.out.println(trie);
    }

}
