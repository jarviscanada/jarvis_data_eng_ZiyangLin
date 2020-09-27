package ca.jrvs.practice.dataStructure.set;

import org.junit.Test;

import static org.junit.Assert.*;

public class JTreeSetTest {

    @Test
    public void size() {
        JSet<Integer> set = new JTreeSet<>();
        set.add(1);
        set.add(3);
        set.add(5);
        set.add(7);
        assertEquals(4, set.size());
    }

    @Test
    public void contains() {
        JSet<Integer> set = new JTreeSet<>();
        set.add(1);
        set.add(3);
        set.add(5);
        set.add(7);
        assertFalse(set.contains(4));
        assertTrue(set.contains(3));
    }

    @Test
    public void remove() {
        JSet<Integer> set = new JTreeSet<>();
        set.add(1);
        set.add(3);
        set.add(5);
        set.add(7);
        set.remove(5);
        assertFalse(set.contains(5));
    }

    @Test
    public void clear() {
        JSet<Integer> set = new JTreeSet<>();
        set.add(1);
        set.add(3);
        set.add(5);
        set.add(7);
        set.clear();
        assertEquals(0, set.size());
    }
}