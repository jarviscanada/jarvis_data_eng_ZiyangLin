package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class OnlyDigitsTest {

    @Test
    public void onlyDigitsAscii() {
        String input1 = "dawd12314";
        String input2 = "7318290";
        String input3 = "241241d.";
        String input4 = "";
        assertFalse(OnlyDigits.onlyDigitsRegex(input1));
        assertTrue(OnlyDigits.onlyDigitsRegex(input2));
        assertFalse(OnlyDigits.onlyDigitsRegex(input3));
        assertFalse(OnlyDigits.onlyDigitsRegex(input4));
    }

    @Test
    public void onlyDigitsApi() {
        String input1 = "dawd12314";
        String input2 = "7318290";
        String input3 = "241241d.";
        String input4 = "";
        assertFalse(OnlyDigits.onlyDigitsRegex(input1));
        assertTrue(OnlyDigits.onlyDigitsRegex(input2));
        assertFalse(OnlyDigits.onlyDigitsRegex(input3));
        assertFalse(OnlyDigits.onlyDigitsRegex(input4));
    }

    @Test
    public void onlyDigitsRegex() {
        String input1 = "dawd12314";
        String input2 = "7318290";
        String input3 = "241241d.";
        String input4 = "";
        assertFalse(OnlyDigits.onlyDigitsRegex(input1));
        assertTrue(OnlyDigits.onlyDigitsRegex(input2));
        assertFalse(OnlyDigits.onlyDigitsRegex(input3));
        assertFalse(OnlyDigits.onlyDigitsRegex(input4));
    }
}