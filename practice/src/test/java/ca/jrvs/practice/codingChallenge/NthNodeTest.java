package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.dataStructure.list.LinkedJList;
import org.junit.Test;

import static org.junit.Assert.*;

public class NthNodeTest {

    @Test
    public void removeNthNode() {
        LinkedJList<Integer> list = new LinkedJList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        int removed = NthNode.removeNthNode(list, 3);
        Object[] actual = list.toArray();
        int[] expected =  {1, 2, 3, 5, 6};
        assertEquals(5, list.size());
        assertEquals(4, removed);
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], actual[1]);
        assertEquals(expected[2], actual[2]);
        assertEquals(expected[3], actual[3]);
        assertEquals(expected[4], actual[4]);
    }
}