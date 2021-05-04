package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class SwapTwoNumbersTest {

    @Test
    public void swapBit() {
        int[] original = {2, 3};
        int[] expected = {3, 2};
        SwapTwoNumbers.swapBit(original);
        assertArrayEquals(expected, original);
    }

    @Test
    public void swapArithmetic() {
        int[] original = {2, 3};
        int[] expected = {3, 2};
        SwapTwoNumbers.swapArithmetic(original);
        assertArrayEquals(expected, original);
    }
}