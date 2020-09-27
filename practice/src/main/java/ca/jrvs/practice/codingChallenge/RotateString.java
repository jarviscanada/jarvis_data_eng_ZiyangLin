package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Rotate-String-ff4f5c4320e54e5582ca33fd06f1e0c9
 */
public class RotateString {

    /**
     * Big-O: Time - O(n^2), Space - O(n)
     * Justification: if second is a rotation of first, then it must appear as a substring in
     *     first + first.
     */
    public static boolean rotateString(String first, String second) {
        if (first.length() != second.length()) {
            return false;
        } else {
            String temp = first + first;
            return temp.contains(second);
        }

    }
}
