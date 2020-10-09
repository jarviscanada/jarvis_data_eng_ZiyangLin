package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class FindDuplicateNumbersTest {

    @Test
    public void findDuplicateSort() {
        int[] array1 = {0, 1, 2, 3, 3};
        int[] array2 = {1, 0, 3, 2, 3};
        assertEquals(3, FindDuplicateNumbers.findDuplicateSort(array1));
        assertEquals(3, FindDuplicateNumbers.findDuplicateSort(array2));
    }

    @Test
    public void findDuplicateSet() {
        int[] array1 = {0, 1, 2, 3, 3};
        int[] array2 = {1, 0, 3, 2, 3};
        assertEquals(3, FindDuplicateNumbers.findDuplicateSet(array1));
        assertEquals(3, FindDuplicateNumbers.findDuplicateSet(array2));
    }
}