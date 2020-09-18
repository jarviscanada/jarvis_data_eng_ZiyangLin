package ca.jrvs.practice.dataStructure.list;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedJListTest {

    @Test
    public void add() {
        JList<String> list = new LinkedJList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        assertEquals("first", list.get(0));
        assertEquals("third", list.get(2));
    }

    @Test
    public void toArray() {
        JList<String> list = new LinkedJList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        String[] expected = {"first", "second", "third"};
        assertEquals(expected, list.toArray());
    }

    @Test
    public void isEmpty() {
        JList<String> list1 = new LinkedJList<>();
        JList<String> list2 = new LinkedJList<>();
        list1.add("first");
        list1.add("second");
        list1.add("third");
        assertTrue(list2.isEmpty());
        assertFalse(list1.isEmpty());
    }

    @Test
    public void indexOf() {
        JList<String> list = new LinkedJList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        assertEquals(1, list.indexOf("second"));
        assertEquals(-1, list.indexOf("zero"));
    }

    @Test
    public void contains() {
        JList<String> list = new LinkedJList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        assertTrue(list.contains("second"));
        assertFalse(list.contains("zero"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get() {
        JList<String> list = new LinkedJList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        assertEquals("second", list.get(1));
        assertEquals("third", list.get(3));
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void remove() {
        JList<String> list = new LinkedJList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        list.remove(1);
        String[] expected = {"first", "third"};
        assertEquals(expected, list.toArray());
        list.remove(3);
    }

    @Test
    public void clear() {
        JList<String> list = new LinkedJList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    public void size() {
        JList<String> list = new LinkedJList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        assertEquals(3, list.size());
    }
}
