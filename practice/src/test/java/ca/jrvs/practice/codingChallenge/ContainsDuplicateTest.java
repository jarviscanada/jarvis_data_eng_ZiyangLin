package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class ContainsDuplicateTest {

    @Test
    public void containsDuplicateSort() {
        int[] array1 = {0, 1, 2, 3, 3};
        int[] array2 = {1, 0, 3, 2, 4};
        assertTrue(ContainsDuplicate.containsDuplicateSort(array1));
        assertFalse(ContainsDuplicate.containsDuplicateSort(array2));
    }

    @Test
    public void containsDuplicateSet() {
        int[] array1 = {0, 1, 2, 3, 3};
        int[] array2 = {1, 0, 3, 2, 4};
        assertTrue(ContainsDuplicate.containsDuplicateSet(array1));
        assertFalse(ContainsDuplicate.containsDuplicateSet(array2));
    }
}