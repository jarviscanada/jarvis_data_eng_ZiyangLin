package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class MissingNumberTest {

    @Test
    public void missingNumberSort() {
        int[] array1 = {0, 1, 2, 3, 5};
        int[] array2 = {1, 0, 3, 2, 5};
        assertEquals(4, MissingNumber.missingNumberSort(array1));
        assertEquals(4, MissingNumber.missingNumberSort(array2));
    }

    @Test
    public void missingNumberSet() {
        int[] array1 = {0, 1, 2, 3, 5};
        int[] array2 = {1, 0, 3, 2, 5};
        assertEquals(4, MissingNumber.missingNumberSet(array1));
        assertEquals(4, MissingNumber.missingNumberSet(array2));
    }

    @Test
    public void missingNumberGauss() {
        int[] array1 = {0, 1, 2, 3, 5};
        int[] array2 = {1, 0, 3, 2, 5};
        assertEquals(4, MissingNumber.missingNumberGauss(array1));
        assertEquals(4, MissingNumber.missingNumberGauss(array2));
    }
}