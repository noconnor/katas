package com.github.noconnor.reference;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<E extends Comparable<? super E>> {

    TreeNode<E> root;


    public boolean insert(E value) {
        TreeNode<E> curr = root;
        int comp = value.compareTo(curr.getData());

        while ((comp < 0 && curr.getLeft() != null) || (comp > 0 && curr.getRight() != null)) {
            if (comp < 0) {
                curr = curr.getLeft();
            } else {
                curr = curr.getRight();
            }
            comp = value.compareTo(curr.getData());
        }

        if (comp < 0) {
            curr.addLeftNode(value);
        } else if (comp > 0) {
            curr.addRightNode(value);
        } else {
            return false;
        }
        return true;
    }

    // BSF
    public boolean contains(E data) {

        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<E> curr = queue.remove();
            if (curr != null) {
                E nodeData = curr.getData();
                if (data.equals(nodeData)) {
                    return true;
                } else if (data.compareTo(nodeData) < 0) {
                    queue.add(curr.getLeft());
                } else {
                    queue.add(curr.getRight());
                }
            }
        }
        return false;
    }

    public boolean containsRecursive(E data) {
        return containsRecursive(data, root);
    }

    private boolean containsRecursive(E data, TreeNode<E> root) {
        if (root != null) {
            if (data.equals(root.getData())) {
                return true;
            } else if (data.compareTo(root.getData()) < 0) {
                return containsRecursive(data, root.getLeft());
            } else {
                return containsRecursive(data, root.getRight());
            }
        }
        return false;
    }

}
