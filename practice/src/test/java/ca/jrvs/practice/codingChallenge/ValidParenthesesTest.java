package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidParenthesesTest {

    @Test
    public void isValidParentheses() {
        String test1 = "";
        String test2 = "(((())){}[[]])";
        String test3 = "((()])";
        String test4 = "((())))";
        assertTrue(ValidParentheses.isValidParentheses(test1));
        assertTrue(ValidParentheses.isValidParentheses(test2));
        assertFalse(ValidParentheses.isValidParentheses(test3));
        assertFalse(ValidParentheses.isValidParentheses(test4));
    }
}