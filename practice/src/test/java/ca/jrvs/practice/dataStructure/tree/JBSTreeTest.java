package ca.jrvs.practice.dataStructure.tree;

import org.junit.Test;

import static org.junit.Assert.*;

public class JBSTreeTest {

    @Test(expected = NullPointerException.class)
    public void insert() {
        JTree<Integer> bst = new JBSTree<>();
        bst.insert(12);
        bst.insert(7);
        bst.insert(9);
        bst.insert(16);
        bst.insert(3);
        bst.insert(10);
        bst.insert(17);
        bst.insert(1);
        bst.insert(8);
        bst.insert(21);
        bst.search(23);
    }
    @Test
    public void search() {
        JTree<Integer> bst = new JBSTree<>();
        bst.insert(12);
        bst.insert(7);
        bst.insert(9);
        bst.insert(16);
        bst.insert(3);
        bst.insert(10);
        bst.insert(17);
        bst.insert(1);
        bst.insert(8);
        bst.insert(21);
        assertEquals(12, (int) bst.search(12));
        assertEquals(3, (int) bst.search(3));
        assertEquals(21, (int) bst.search(21));
    }

    @Test
    public void remove() {
        JTree<Integer> bst = new JBSTree<>();
        bst.insert(12);
        bst.insert(7);
        bst.insert(9);
        bst.insert(16);
        bst.insert(3);
        bst.insert(10);
        bst.insert(17);
        bst.insert(1);
        bst.insert(8);
        bst.insert(21);
        assertEquals(12, (int) bst.remove(12));
    }

    @Test
    public void preOrder() {
        JTree<Integer> bst = new JBSTree<>();
        bst.insert(12);
        bst.insert(7);
        bst.insert(9);
        bst.insert(16);
        bst.insert(3);
        bst.insert(10);
        bst.insert(17);
        bst.insert(1);
        bst.insert(8);
        bst.insert(21);
        bst.preOrder();
    }

    @Test
    public void inOrder() {
        JTree<Integer> bst = new JBSTree<>();
        bst.insert(12);
        bst.insert(7);
        bst.insert(9);
        bst.insert(16);
        bst.insert(3);
        bst.insert(10);
        bst.insert(17);
        bst.insert(1);
        bst.insert(8);
        bst.insert(21);
        bst.inOrder();
    }

    @Test
    public void postOrder() {
        JTree<Integer> bst = new JBSTree<>();
        bst.insert(12);
        bst.insert(7);
        bst.insert(9);
        bst.insert(16);
        bst.insert(3);
        bst.insert(10);
        bst.insert(17);
        bst.insert(1);
        bst.insert(8);
        bst.insert(21);
        bst.postOrder();
    }

    @Test
    public void findHeight() {
        JTree<Integer> bst = new JBSTree<>();
        bst.insert(12);
        bst.insert(7);
        bst.insert(9);
        bst.insert(16);
        bst.insert(3);
        bst.insert(10);
        bst.insert(17);
        bst.insert(1);
        bst.insert(8);
        bst.insert(21);
        assertEquals(4, bst.findHeight());
    }
}