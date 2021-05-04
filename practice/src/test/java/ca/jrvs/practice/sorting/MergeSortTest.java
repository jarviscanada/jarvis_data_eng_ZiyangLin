package ca.jrvs.practice.sorting;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MergeSortTest {

    @Test
    public void mergeSort() {
        int[] actual = {6, 4, 5, 8, 3, 7, 1, 9, 8};
        MergeSort.mergeSort(actual, actual.length);
        System.out.println(Arrays.toString(actual));
    }
}