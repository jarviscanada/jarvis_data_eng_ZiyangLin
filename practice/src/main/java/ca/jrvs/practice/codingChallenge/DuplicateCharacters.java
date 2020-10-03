package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Map;

/**
 * ticket: https://www.notion.so/Duplicate-Characters-1724e4ca49014c25ab0685a67aa1638a
 */
public class DuplicateCharacters {

    /**
     * Big-O: Time - O(n) + O(n) + O(n) --> O(n); Space - O(n)
     * Justification: this algorithm uses a HashMap to store char and its count as key-value pairs,
     *     then populate the output Array with only the characters with count > 1
     */
    public static String[] duplicateCharacters(String input) {
        input = input.trim();

        // configure char-count map.
        Map<Character, Integer> charMap = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            if (!charMap.containsKey(input.charAt(i))) {
                charMap.put(input.charAt(i), 1);
            } else {
                charMap.put(input.charAt(i), charMap.get(input.charAt(i)) + 1);
            }
        }

        // check size of the map.
        int size = 0;
        for (char character: charMap.keySet()) {
            if (charMap.get(character) > 1) {
                size++;
            }
        }

        int i = 0;
        String[] result = new String[size];
        // populate output Array with only characters that have count > 1
        for (char character: charMap.keySet()) {
            if (charMap.get(character) > 1) {
                result[i] = Character.toString(character);
                i++;
            }
        }

        return result;
    }
}
