package com.github.noconnor.reference;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class BinaryTree<E> {

    private TreeNode<E> root;

    public void preOrder() {
        preOrder(root);
    }

    public void postOrder() {
        postOrder(root);
    }

    public void inOrder() {
        inOrder(root);
    }

    // BFS
    public void levelOrder() {
        Queue<TreeNode<E>> queue = new LinkedBlockingQueue<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode<E> curr = queue.remove();
            if (curr != null) {
                curr.visit();
                queue.add(curr.getLeft());
                queue.add(curr.getRight());
            }
        }
    }

    private void preOrder(TreeNode<E> start) {
        if (start != null) {
            start.visit();
            preOrder(start.getLeft());
            preOrder(start.getRight());
        }
    }

    private void postOrder(TreeNode<E> start) {
        if (start != null) {
            postOrder(start.getLeft());
            postOrder(start.getRight());
            start.visit();
        }
    }

    private void inOrder(TreeNode<E> start) {
        if (start != null) {
            inOrder(start.getLeft());
            start.visit();
            inOrder(start.getRight());
        }
    }


}
