package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringToAoiTest {

    @Test
    public void stringToAoiParse() {
        String input1 = "awdw312d";
        String input2 = "13124dsad";
        String input3 = " wdfnjdhibfqed.;,;f";
        String input4 = "   46...   ";
        String input5 = "   46   ";
        assertEquals(312, StringToAoi.stringToAoiParse(input1));
        assertEquals(13124, StringToAoi.stringToAoiParse(input2));
        assertEquals(0, StringToAoi.stringToAoiParse(input3));
        assertEquals(46, StringToAoi.stringToAoiParse(input4));
        assertEquals(46, StringToAoi.stringToAoiParse(input5));
    }

    @Test
    public void stringToAoiWithoutParse() {
        String input1 = "awdw312d";
        String input2 = "13124dsad";
        String input3 = " wdfnjdhibfqed.;,;f";
        String input4 = "   46...   ";
        String input5 = "   46   ";
        assertEquals(312, StringToAoi.stringToAoiParse(input1));
        assertEquals(13124, StringToAoi.stringToAoiParse(input2));
        assertEquals(0, StringToAoi.stringToAoiParse(input3));
        assertEquals(46, StringToAoi.stringToAoiParse(input4));
        assertEquals(46, StringToAoi.stringToAoiParse(input5));
    }
}