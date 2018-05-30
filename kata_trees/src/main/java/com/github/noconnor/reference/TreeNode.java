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

    public TreeNode<E> getParent() {
        return parent;
    }

    public TreeNode<E> getLeft() {
        return this.left;
    }

    public void setLeft(TreeNode<E> left) {
        this.left = left;
    }

    public TreeNode<E> getRight() {
        return this.right;
    }

    public void setRight(TreeNode<E> right) {
        this.right = right;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public void visit() {
        // do nothing
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
