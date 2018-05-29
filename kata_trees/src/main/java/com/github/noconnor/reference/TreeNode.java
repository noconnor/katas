package com.github.noconnor.reference;

public class TreeNode<E> {

    private E data;
    private TreeNode<E> parent;
    private TreeNode<E> left;
    private TreeNode<E> right;

    public TreeNode(E val, TreeNode<E> parent) {
        this.parent = parent;
        this.data = val;
        this.left = null;
        this.right = null;
    }

    public TreeNode<E> addLeftNode(E val) {
        this.left = new TreeNode<>(val, this);
        return this.left;
    }

    public TreeNode<E> addRightNode(E val) {
        this.right = new TreeNode<>(val, this);
        return this.right;
    }

    public TreeNode<E> getLeft() {
        return this.left;
    }

    public TreeNode<E> getRight() {
        return this.right;
    }

    public E getData(){
        return data;
    }

    public void visit() {
        // do nothing
    }

}
