package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class TwoSumTest {

    @Test
    public void twoSumBruteForce() {
        int[] nums = {1, 2, 3, 6, 11, 13};
        int[] actual1 = TwoSum.twoSumBruteForce(nums, 7);
        int[] actual2 = TwoSum.twoSumBruteForce(nums, 10);
        assertEquals(0, actual1[0]);
        assertEquals(3, actual1[1]);
        assertEquals(-1, actual2[0]);
        assertEquals(-1, actual2[1]);
    }

    @Test
    public void twoSumSorted() {
        int[] nums = {1, 2, 3, 6, 11, 13};
        int[] actual1 = TwoSum.twoSumSorted(nums, 7);
        int[] actual2 = TwoSum.twoSumSorted(nums, 10);
        assertEquals(0, actual1[0]);
        assertEquals(3, actual1[1]);
        assertEquals(-1, actual2[0]);
        assertEquals(-1, actual2[1]);
    }

    @Test
    public void twoSumHashMap() {
        int[] nums = {1, 2, 3, 6, 11, 13};
        int[] actual1 = TwoSum.twoSumHashMap(nums, 7);
        int[] actual2 = TwoSum.twoSumHashMap(nums, 10);
        assertEquals(0, actual1[0]);
        assertEquals(3, actual1[1]);
        assertEquals(-1, actual2[0]);
        assertEquals(-1, actual2[1]);
    }
}