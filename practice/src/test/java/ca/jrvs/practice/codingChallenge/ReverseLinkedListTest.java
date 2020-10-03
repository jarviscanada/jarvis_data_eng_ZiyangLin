package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.dataStructure.list.LinkedJList;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class ReverseLinkedListTest {

    @Test
    public void reverseIterative() {
        LinkedJList<Integer> list = new LinkedJList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        LinkedJList<Integer>.Node newHead = ReverseLinkedList.reverseIterative(list.getHead());
        assertEquals(newHead.getData().intValue(), 4);
        newHead = newHead.getNext();
        assertEquals(newHead.getData().intValue(), 3);
        newHead = newHead.getNext();
        assertEquals(newHead.getData().intValue(), 2);
        newHead = newHead.getNext();
        assertEquals(newHead.getData().intValue(), 1);
        newHead = newHead.getNext();
        assertNull(newHead);
    }

    @Test
    public void reverseRecursive() {
        LinkedJList<Integer> list = new LinkedJList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        LinkedJList<Integer>.Node newHead = ReverseLinkedList.reverseRecursive(list.getHead());
        assertEquals(newHead.getData().intValue(), 4);
        newHead = newHead.getNext();
        assertEquals(newHead.getData().intValue(), 3);
        newHead = newHead.getNext();
        assertEquals(newHead.getData().intValue(), 2);
        newHead = newHead.getNext();
        assertEquals(newHead.getData().intValue(), 1);
        newHead = newHead.getNext();
        assertNull(newHead);
    }
}