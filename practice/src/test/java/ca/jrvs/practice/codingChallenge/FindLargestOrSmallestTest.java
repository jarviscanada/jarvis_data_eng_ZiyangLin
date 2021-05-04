package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class FindLargestOrSmallestTest {

    @Test
    public void findMaxOrMinLoop() {
        int[] nums = {1, 6, 2, 5, 3, 8, 7, 10, 7, 3};
        assertEquals(10, FindLargestOrSmallest.findMaxOrMinLoop(nums, "Max"));
        assertEquals(1, FindLargestOrSmallest.findMaxOrMinLoop(nums, "Min"));
    }

    @Test
    public void findMaxOrMinStream() {
        int[] nums = {1, 6, 2, 5, 3, 8, 7, 10, 7, 3};
        assertEquals(10, FindLargestOrSmallest.findMaxOrMinStream(nums, "Max"));
        assertEquals(1, FindLargestOrSmallest.findMaxOrMinStream(nums, "Min"));
    }

    @Test
    public void findMaxOrMinApi() {
        int[] nums = {1, 6, 2, 5, 3, 8, 7, 10, 7, 3};
        assertEquals(10, FindLargestOrSmallest.findMaxOrMinApi(nums, "Max"));
        assertEquals(1, FindLargestOrSmallest.findMaxOrMinApi(nums, "Min"));
    }
}