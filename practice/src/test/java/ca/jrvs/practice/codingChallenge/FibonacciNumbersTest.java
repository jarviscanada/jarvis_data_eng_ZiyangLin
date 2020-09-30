package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class FibonacciNumbersTest {

    @Test
    public void fibonacciRecursion() {
        assertEquals(0, FibonacciNumbers.fibonacciRecursion(0));
        assertEquals(1, FibonacciNumbers.fibonacciRecursion(1));
        assertEquals(1, FibonacciNumbers.fibonacciRecursion(2));
        assertEquals(3, FibonacciNumbers.fibonacciRecursion(4));
        assertEquals(21, FibonacciNumbers.fibonacciRecursion(8));
    }

    @Test
    public void fibonacciDP() {
        assertEquals(0, FibonacciNumbers.fibonacciDP(0));
        assertEquals(1, FibonacciNumbers.fibonacciDP(1));
        assertEquals(1, FibonacciNumbers.fibonacciDP(2));
        assertEquals(3, FibonacciNumbers.fibonacciDP(4));
        assertEquals(21, FibonacciNumbers.fibonacciDP(8));
    }
}