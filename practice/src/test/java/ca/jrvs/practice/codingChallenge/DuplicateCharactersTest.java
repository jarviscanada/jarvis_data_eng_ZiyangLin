package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class DuplicateCharactersTest {

    @Test
    public void duplicateCharacters() {
        String input1 = "adwvvbadw";
        String[] input1Expected = {Character.toString('a'), Character.toString('d'),
                Character.toString('v'), Character.toString('w')};
        assertEquals(input1Expected, DuplicateCharacters.duplicateCharacters(input1));
        String input2 = "   abcdefgal ";
        String[] input2Expected = {Character.toString('a')};
        assertEquals(input2Expected, DuplicateCharacters.duplicateCharacters(input2));
        String input3 = "abcdef213g";
        String[] input3Expected = {};
        assertEquals(input3Expected, DuplicateCharacters.duplicateCharacters(input3));
    }
}