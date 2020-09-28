package ca.jrvs.practice.search;

import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;

public class BinarySearchTest {

    @Test
    public void binarySearchRecursion() {
        int[] arr = {1, 3, 5, 7, 8, 12, 16, 21, 22, 25};
        assertEquals(Optional.of(2), BinarySearch.binarySearchIteration(arr, 5));
        assertEquals(Optional.of(9), BinarySearch.binarySearchIteration(arr, 25));
        assertEquals(Optional.of(0), BinarySearch.binarySearchIteration(arr, 1));
        assertEquals(Optional.empty(), BinarySearch.binarySearchIteration(arr, 4));
    }

    @Test
    public void binarySearchIteration() {
        int[] arr = {1, 3, 5, 7, 8, 12, 16, 21, 22, 25};
        assertEquals(Optional.of(2), BinarySearch.binarySearchIteration(arr, 5));
        assertEquals(Optional.of(9), BinarySearch.binarySearchIteration(arr, 25));
        assertEquals(Optional.of(0), BinarySearch.binarySearchIteration(arr, 1));
        assertEquals(Optional.empty(), BinarySearch.binarySearchIteration(arr, 4));
    }
}