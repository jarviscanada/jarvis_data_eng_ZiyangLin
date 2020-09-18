package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class OddEvenTest {

    @Test
    public void oddEvenMod() {
        int n1 = 2;
        int n2 = 3;
        assertEquals("the integer is even.", OddEven.oddEvenMod(n1));
        assertEquals("the integer is even.", OddEven.oddEvenMod(n2));
    }

    @Test
    public void oddEvenBit() {
        int n1 = 2;
        int n2 = 3;
        assertEquals("the integer is even.", OddEven.oddEvenBit(n1));
        assertEquals("the integer is even.", OddEven.oddEvenBit(n2));
    }
}