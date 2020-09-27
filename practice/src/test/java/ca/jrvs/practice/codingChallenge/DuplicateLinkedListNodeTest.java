package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.dataStructure.list.LinkedJList;
import org.junit.Test;

import static org.junit.Assert.*;

public class DuplicateLinkedListNodeTest {

    @Test
    public void removeDuplicates() {
        LinkedJList<Integer> list = new LinkedJList<>();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(4);
        list.add(6);
        DuplicateLinkedListNode.removeDuplicates(list);
        assertTrue(list.contains(2));
        assertEquals(4, list.size());
    }
}