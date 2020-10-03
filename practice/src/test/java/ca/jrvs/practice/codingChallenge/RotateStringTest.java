package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;


public class RotateStringTest {

    @Test
    public void rotateString() {
        String first = "abcde";
        String second1 = "bcdea";
        String second2 = "bcde";
        String second3 = "eabcd";
        String second4 = "eabce";
        assertTrue(RotateString.rotateString(first, second1));
        assertFalse(RotateString.rotateString(first, second2));
        assertTrue(RotateString.rotateString(first, second3));
        assertFalse(RotateString.rotateString(first, second4));
    }
}