package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidAnagramTest {

    @Test
    public void isAnagramSort() {
        String str1 = "";
        String str1Compare = "";
        String str2 = "anagram";
        String str2Compare = "ngramaa";
        String str3 = "anagra";
        String str3Compare = "annaana";
        assertTrue(ValidAnagram.isAnagramSort(str2, str2Compare));
        assertTrue(ValidAnagram.isAnagramSort(str1, str1Compare));
        assertFalse(ValidAnagram.isAnagramSort(str2, str3));
        assertFalse(ValidAnagram.isAnagramSort(str2, str3Compare));
    }

    @Test
    public void isAnagramMap() {
        String str1 = "";
        String str1Compare = "";
        String str2 = "anagram";
        String str2Compare = "ngramaa";
        String str3 = "anagra";
        String str3Compare = "annaana";
        assertTrue(ValidAnagram.isAnagramMap(str2, str2Compare));
        assertTrue(ValidAnagram.isAnagramMap(str1, str1Compare));
        assertFalse(ValidAnagram.isAnagramMap(str2, str3));
        assertFalse(ValidAnagram.isAnagramMap(str2, str3Compare));
    }
}