package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Print-letter-with-number-9e69c2b593af42908bd463980c9e2e96
 */
public class PrintLetterWithNumber {

    /**
     * Big-O: Time complexity O(n), space complexity O(n)
     * Justification: check integer values of characters agains ASCII table, and append accordingly.
     */
    public static String printLetterWithNumber(String input) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            result.append(input.charAt(i));
            if (97 <= input.charAt(i) && input.charAt(i) <= 122) {
                result.append((int) input.charAt(i) - 96);
            } else {
                result.append((int) input.charAt(i) - 38);
            }
        }

        return result.toString();
    }
}
