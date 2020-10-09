package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class PrintLetterWithNumberTest {

    @Test
    public void printLetterWithNumber() {
        String input = "abcXYZ";
        assertEquals("a1b2c3X50Y51Z52", PrintLetterWithNumber.printLetterWithNumber(input));
    }
}