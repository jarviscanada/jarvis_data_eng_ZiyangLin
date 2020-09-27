package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class RemoveElementTest {

    @Test
    public void removeElement() {
        int[] arr1 = {1, 2, 2, 3, 5};
        assertEquals(3, RemoveElement.removeElement(arr1, 2));
        assertEquals(4, RemoveElement.removeElement(arr1, 1));
        assertEquals(5, RemoveElement.removeElement(arr1, 0));
    }

}