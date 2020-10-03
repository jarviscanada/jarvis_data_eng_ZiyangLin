package ca.jrvs.practice.dataStructure.tree;

import java.util.*;

/**
 * A simplified BST implementation
 *
 * @param <E> type of object to be stored
 */
public class JBSTree<E> implements JTree<E> {

    private Node<E> root;

    /**
     * Create a new BST
     *
     */
    public JBSTree() {
        this.root = null;
    }

    /**
     * Insert an object into the BST.
     * Please review the BST property.
     *
     * @param e item to be inserted
     * @return inserted item
     * @throws IllegalArgumentException if the object already exists
     */
    @Override
    public E insert(E e) {
        if (root == null) {
            root = new Node<>(e, null);
        } else {
            root.add(e);
        }
        return e;
    }

    /**
     * Search and return an object, return null if not found
     *
     * @param e to be found
     * @return the object if exists or null if not
     */
    @Override
    public E search(E e) {
        if (root == null) {
            throw new NullPointerException("error: this tree is currently empty");
        } else {
            return root.find(e);
        }
    }

    /**
     * Remove an object from the tree.
     *
     * @param e to be removed
     * @return removed object
     * @throws IllegalArgumentException if the object not exists
     */
    @Override
    public E remove(E e) {
        if (root == null) {
            throw new NullPointerException("error: this tree is currently empty");
        } else {
            root.delete(e);
        }
        return e;
    }

    /**
     * traverse the tree recursively
     *
     * @return all objects in pre-order
     */
    @Override
    public void preOrder() {
        if (root == null) {
            System.out.println("the tree is empty.");
        } else {
            root.preOrder();
        }
    }

    /**
     * traverse the tree recursively
     *
     * @return all objects in-order
     */
    @Override
    public void inOrder() {
        if (root == null) {
            System.out.println("the tree is empty.");
        } else {
            root.inOrder();
        }
    }

    /**
     * traverse the tree recursively
     *
     * @return all objects pre-order
     */
    @Override
    public void postOrder() {
        if (root == null) {
            System.out.println("the tree is empty.");
        } else {
            root.postOrder();
        }
    }

    /**
     * traverse through the tree and find out the tree height
     * @return height
     * @throws NullPointerException if the BST is empty
     */
    @Override
    public int findHeight() {
        if (root == null) {
            return 0;
        } else {
            return root.height();
        }
    }

    static final class Node<E> implements Comparable<E> {
        public E value;
        public Node<E> left;
        public Node<E> right;
        public Node<E> parent;

        public Node(E value, Node<E> parent) {
            this.value = value;
            this.parent = parent;
        }

        public E find(E value) {
            if (value.equals(this.value)) {
                return this.value;
            } else if (compareTo(value) < 0 && left != null){
                return left.find(value);
            } else if (compareTo(value) > 0 && right != null) {
                return right.find(value);
            }
            throw new NullPointerException("error: value does not exist");
        }

        public void add(E value) {
            if (compareTo(value) > 0) {
                if (this.right == null) {
                    this.right = new Node<>(value, this);
                } else {
                    this.right.add(value);
                }
            } else if (compareTo(value) < 0) {
                if (this.left == null) {
                    this.left = new Node<>(value, this);
                } else {
                    this.left.add(value);
                }
            } else {
                throw new IllegalArgumentException("error: value already exists");
            }
        }

        public void delete(E value) {
            // case 1: a node has no children
            if (this.equals(value)) {
                deleteRoot();
            } else if (this.compareTo(value) < 0) {
                this.left.delete(value);
            } else {
                this.right.delete(value);
            }

        }

        public void deleteRoot() {
            // case 1: a node has no children
            if (this.left == null && this.right == null) {
                this.value = null;
            } else if (this.left == null) {
                this.value = this.right.extractMin();
            } else {
                this.value = this.right.extractMax();
            }
        }

        public E extractMax() {
            if (this.right == null) {
                E temp = this.value;
                this.value = this.left.value;
                this.left = this.left.left;
                this.right = this.left.right;
                return temp;
            } else {
                return this.right.extractMax();
            }
        }

        public E extractMin() {
            if (this.left == null) {
                E temp = this.value;
                this.value = this.right.value;
                this.left = this.right.left;
                this.right = this.right.right;
                return temp;
            } else {
                return this.left.extractMin();
            }
        }

        public int height() {
            if (this.left == null && this.right == null) {
                return 1;
            } else {
                return Math.max(this.left.height(), this.right.height()) + 1;
            }
        }

        public void preOrder() {
            if (this.value == null) {
                System.out.println("the tree is empty");
            } else {
                System.out.println(this.value + " ");
                this.left.preOrder();
                this.right.preOrder();
            }
        }

        public void inOrder() {
            if (this.value == null) {
                System.out.println("the tree is empty");
            } else {
                this.left.inOrder();
                System.out.println(this.value + " ");
                this.right.inOrder();
            }
        }

        public void postOrder() {
            if (this.value == null) {
                System.out.println("the tree is empty");
            } else {
                this.left.postOrder();
                this.right.postOrder();
                System.out.println(this.value + " ");
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(value, node.value) &&
                    Objects.equals(left, node.left) &&
                    Objects.equals(right, node.right) &&
                    Objects.equals(parent, node.parent);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, left, right, parent);
        }

        @Override
        public int compareTo(E o) {
            return Integer.compare(o.hashCode(), this.value.hashCode());
        }
    }
}
