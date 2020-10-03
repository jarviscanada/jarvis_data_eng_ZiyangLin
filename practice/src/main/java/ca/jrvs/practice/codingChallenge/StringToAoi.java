package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/String-to-Integer-atoi-e7df989db51c4a509c0b07216ebd9283
 */
public class StringToAoi {

    /**
     * Big-O: Time - O(n); Space - O(n)
     * Justification: this algorithm first iterates to the first digit, and populate a temp string to be
     *     parsed into int for return. Added exception handling for empty string.
     */
    public static int stringToAoiParse(String input) {
        input = input.trim();
        int index = 0;
        StringBuilder temp = new StringBuilder();
        // iterate to the first digit.
        while (index < input.length() && !(57 >= (int) input.charAt(index) && (int) input.charAt(index) >= 48)) {
            index++;
        }
        // build the integer number from the first digit.
        while (index < input.length() && 57 >= (int) input.charAt(index) && (int) input.charAt(index) >= 48) {
            temp.append(input.charAt(index));
            index++;
        }

        // exception handling for the situation with empty string.
        try {
            return Integer.parseInt(temp.toString());
        } catch (NumberFormatException ex) {
            return 0;
        }

    }

    /**
     * Big-O: Time - O(n); Space - O(n)
     * Justification: same iteration, instead using Integer.parseInt(), build result by arithmetic operations.
     */
    public static int stringToAoiWithoutParse(String input) {
        input = input.trim();
        int index = 0;
        StringBuilder temp = new StringBuilder();
        // iterate to the first digit.
        while (index < input.length() && !(57 >= (int) input.charAt(index) && (int) input.charAt(index) >= 48)) {
            index++;
        }
        // build the integer number from the first digit.
        while (index < input.length() && 57 >= (int) input.charAt(index) && (int) input.charAt(index) >= 48) {
            temp.append(input.charAt(index));
            index++;
        }

        if (temp.length() == 0) {
            return 0;
        } else {
            int result = 0;
            for (int i = 0; i < temp.length(); i++) {
                int digit = (int) temp.charAt(i) - 48;
                result += digit * Math.pow(10, temp.length() - i - 1);
            }
            return result;
        }
    }

}
