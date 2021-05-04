package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class MergeSortedArrayTest {

    @Test
    public void mergeSortedArray() {
        int[] dest1 = new int[6];
        int[] dest2 = new int[6];
        int[] src1 = {2, 5, 6};
        int[] src2 = {1, 1, 1};
        dest1[0] = 1;
        dest1[1] = 2;
        dest1[2] = 3;
        dest2[0] = 2;
        dest2[1] = 4;
        dest2[2] = 6;
        MergeSortedArray.mergeSortedArray(dest1, src1);
        MergeSortedArray.mergeSortedArray(dest2, src2);
        int[] expected1 = {1, 2, 2, 3, 5, 6};
        int[] expected2 = {1, 1, 1, 2, 4, 6};
        assertArrayEquals(expected1, dest1);
        assertArrayEquals(expected2, dest2);
    }
}