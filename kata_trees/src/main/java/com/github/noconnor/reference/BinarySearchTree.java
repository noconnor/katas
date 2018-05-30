package com.github.noconnor.reference;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<E extends Comparable<? super E>> {

    private TreeNode<E> root;

    public void delete(E value) {
        delete(value, root);
    }

    private TreeNode<E> delete(E value, TreeNode<E> node) {
        if (node == null) {
            return null;
        }

        if (value.compareTo(node.getData()) < 0) {
            node.setLeft(delete(value, node.getLeft()));
        } else if (value.compareTo(node.getData()) > 0) {
            node.setRight(delete(value, node.getRight()));
        } else {

            if (node.getRight() == null) {
                return node.getLeft();
            } else if (node.getLeft() == null) {
                return node.getRight();
            }

            E minNodeValue = minValue(node.getRight());
            node.setData(minNodeValue);
            node.setRight(delete(minNodeValue, node.getRight()));
        }

        return node;
    }

    private E minValue(TreeNode<E> node) {
        E value = node.getData();
        while (node.getLeft() != null) {
            value = node.getLeft().getData();
            node = node.getLeft();
        }
        return value;
    }


    public boolean insert(E value) {

        if (root == null) {
            root = new TreeNode<>(value, null);
            return true;
        }
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

    public void print() {
        System.out.println();
        print(root, 0, "");
        System.out.println();
    }

    public void print(TreeNode<E> node, int level, String separator) {
        if (node != null) {
            // reverse in order traversal
            print(node.getRight(), level + 1, "/");

            if (level == 0) {
                System.out.println("  (" + node.getData() + ")");
            } else {
                for (int i = 0; i < level; i++) {
                    System.out.print("    ");
                }
                System.out.println(separator + "-(" + node.getData() + ")");
            }

            print(node.getLeft(), level + 1, "\\");
        }

    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        /*
               50
           /      \
          30      70
         /  \    /   \
        20  40  60   80
        */
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(21);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);
        tree.insert(79);
        tree.insert(45);
        tree.insert(22);

        System.out.println("\nFull tree");
        tree.print();

        System.out.println("\nDeleting [20]");
        tree.delete(20);
        tree.print();

        System.out.println("\nDeleting [70]");
        tree.delete(70);
        tree.print();

        System.out.println("\nDeleting [50]");
        tree.delete(50);
        tree.print();
    }

}
