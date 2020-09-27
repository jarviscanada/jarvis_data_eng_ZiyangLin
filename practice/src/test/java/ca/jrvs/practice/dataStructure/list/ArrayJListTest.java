package ca.jrvs.practice.dataStructure.list;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayJListTest {

    @Test
    public void add() {
        JList<String> list = new ArrayJList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        assertEquals("first", list.get(0));
    }

    @Test
    public void toArray() {
        JList<String> list = new ArrayJList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        String[] expected = {"first", "second", "third"};
        assertEquals(expected, list.toArray());
    }

    @Test
    public void isEmpty() {
        JList<String> list = new ArrayJList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        assertFalse(list.isEmpty());
    }

    @Test
    public void indexOf() {
        JList<String> list = new ArrayJList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        assertEquals(2, list.indexOf("third"));
        assertEquals(-1, list.indexOf("zero"));
    }

    @Test
    public void contains() {
        JList<String> list = new ArrayJList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        assertFalse(list.contains("zero"));
        assertTrue(list.contains("second"));
    }

    @Test
    public void get() {
        JList<String> list = new ArrayJList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        assertEquals("third", list.get(2));
    }

    @Test
    public void remove() {
        JList<String> list = new ArrayJList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        list.remove(1);
        String[] expected = {"first", "third"};
        assertEquals(expected, list.toArray());
    }

    @Test
    public void clear() {
        JList<String> list = new ArrayJList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        list.clear();
        assertEquals(0, list.size());
    }
}