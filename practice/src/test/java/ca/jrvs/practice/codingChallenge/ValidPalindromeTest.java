package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidPalindromeTest {

    @Test
    public void isValidPalindrome() {
        String input1 = "A man, a plan, a canal: Panama";
        String input2 = "race a car";
        assertTrue(ValidPalindrome.isValidPalindrome(input1));
        assertFalse(ValidPalindrome.isValidPalindrome(input2));
    }

    @Test
    public void isValidPalindromeRecursion() {
        String input1 = "A man, a plan, a canal: Panama";
        String input2 = "race a car";
        input1 = ValidPalindrome.processInput(input1);
        input2 = ValidPalindrome.processInput(input2);
        assertTrue(ValidPalindrome.isValidPalindromeRecursion(input1));
        assertFalse(ValidPalindrome.isValidPalindromeRecursion(input2));
    }
}