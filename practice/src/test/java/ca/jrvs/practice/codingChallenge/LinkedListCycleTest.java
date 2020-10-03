package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.dataStructure.list.LinkedJList;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListCycleTest {

    @Test
    public void hasCycle() {
        LinkedJList<Integer> list1 = new LinkedJList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(5);
        list1.add(6);
        assertFalse(LinkedListCycle.hasCycle(list1));
        LinkedJList<Integer> list2 = new LinkedJList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        // set the linked list to become 1 -> 2 -> 3 -> 2 -> 3 -> ...
        LinkedJList<Integer>.Node last = list2.getHead().getNext().getNext();
        last.setNext(list2.getHead().getNext());
        assertTrue(LinkedListCycle.hasCycleHashMap(list2));
    }

    @Test
    public void hasCycleHashMap() {
        LinkedJList<Integer> list = new LinkedJList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        // set the linked list to become 1 -> 2 -> 3 -> 2 -> 3 -> ...
        LinkedJList<Integer>.Node last = list.getHead().getNext().getNext();
        last.setNext(list.getHead().getNext());
        assertTrue(LinkedListCycle.hasCycleHashMap(list));
    }
}