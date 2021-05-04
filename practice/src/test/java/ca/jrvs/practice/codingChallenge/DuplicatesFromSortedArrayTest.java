package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class DuplicatesFromSortedArrayTest {

    @Test
    public void removeDuplicates() {
        int[] input1 = {1, 1, 2};
        int[] input2 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int[] expected = {0 ,1, 2, 3, 4, -1, -1, -1, -1, -1};
        assertEquals(2, DuplicatesFromSortedArray.removeDuplicates(input1));
        assertEquals(5, DuplicatesFromSortedArray.removeDuplicates(input2));
        assertArrayEquals(input2, expected);
    }
}