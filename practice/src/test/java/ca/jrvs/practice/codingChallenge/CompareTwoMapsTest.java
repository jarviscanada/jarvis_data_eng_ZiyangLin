package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class CompareTwoMapsTest {

    @Test
    public void compareMaps() {
        HashMap<Integer, String> hashMap1 = new HashMap<>();
        HashMap<Integer, String> hashMap2 = new HashMap<>();
        HashMap<Integer, String> hashMap3 = new HashMap<>();
        HashMap<Integer, String> hashMap4 = new HashMap<>();
        HashMap<Integer, String> hashMap5 = new HashMap<>();
        hashMap1.put(1, "foo");
        hashMap1.put(2, "hee");
        hashMap2.put(2, "hee");
        hashMap2.put(1, "foo");
        hashMap3.put(1, "foo");
        hashMap4.put(1, "foo");
        hashMap4.put(2, "kaa");
        hashMap5.put(1, "foo");
        hashMap5.put(2, "hee");
        hashMap5.put(3, "kaa");
        assertTrue(CompareTwoMaps.compareMaps(hashMap1, hashMap2));
        assertFalse(CompareTwoMaps.compareMaps(hashMap1, hashMap3));
        assertFalse(CompareTwoMaps.compareMaps(hashMap1, hashMap4));
        assertFalse(CompareTwoMaps.compareMaps(hashMap1, hashMap5));
    }
}