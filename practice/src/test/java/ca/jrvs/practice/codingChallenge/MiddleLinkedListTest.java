package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.dataStructure.list.LinkedJList;
import org.junit.Test;

import static org.junit.Assert.*;

public class MiddleLinkedListTest {

    @Test
    public void middleLinkedList() {
        LinkedJList<Integer> list = new LinkedJList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        int actual1 = MiddleLinkedList.middleLinkedList(list);
        list.remove(4);
        int actual2 = MiddleLinkedList.middleLinkedList(list);
        assertEquals(5, list.size());
        assertEquals(4, actual1);
        assertEquals(3, actual2);
    }
}