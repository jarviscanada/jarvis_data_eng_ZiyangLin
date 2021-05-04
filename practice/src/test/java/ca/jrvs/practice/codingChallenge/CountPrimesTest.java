package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class CountPrimesTest {

    @Test
    public void countPrimes() {
        assertEquals(0, CountPrimes.countPrimes(2));
        assertEquals(1, CountPrimes.countPrimes(3));
        assertEquals(4, CountPrimes.countPrimes(11));
        assertEquals(5, CountPrimes.countPrimes(12));
        assertEquals(30, CountPrimes.countPrimes(120));
    }
}