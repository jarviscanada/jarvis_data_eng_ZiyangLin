package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashMap;

/**
 * ticket: https://www.notion.so/Valid-Anagram-e5ec76cd7cfe46e6829e57ad8dd31ad7
 */
public class ValidAnagram {

    /**
     * Big-O: Time - O(nlog(n)) from sorting, Space - O(1)
     * Justification: sort both strings, they are anagram iff the sorted versions equal.
     */
    public static boolean isAnagramSort(String str1, String str2) {
        if (str1.equals("") && str2.equals("")) {
            return true;
        } else if (str1.length() != str2.length()) {
            return false;
        }

        char[] str1Array = str1.toCharArray();
        char[] str2Array = str2.toCharArray();
        Arrays.sort(str1Array);
        Arrays.sort(str2Array);
        return Arrays.equals(str1Array, str2Array);
    }

    /**
     * Big-O: Time - O(n), Space - O(1)
     * Justification: use a map to record letter count for str1, and use str2 to decrement.
     *                The 2 strings are anagram iff the result map has 0 for all 26 letters.
     * Note: we use an int[] instead of HashMap<Character, Integer> to ensure that the space complexity
     *       is O(1) since we are sure that there are only 26 letters needed to be counted.
     */
    public static boolean isAnagramMap(String str1, String str2) {
        if (str1.equals("") && str2.equals("")) {
            return true;
        } else if (str1.length() != str2.length()) {
            return false;
        }

        int[] counts = new int[26];
        for (int i = 0; i < str1.length(); i++) {
            // str1.charAt(i) - 'a' to use ASCII to identify the index
            counts[str1.charAt(i) - 'a'] += 1;
        }
        for (int j = 0; j < str2.length(); j++) {
            counts[str2.charAt(j) - 'a'] -= 1;
        }

        for (int count : counts) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

}
