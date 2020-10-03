package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Check-if-a-String-contains-only-digits-678cd70f46c34f1e8093dda7858bb306
 */
public class OnlyDigits {

    /**
     * Big-O: Time - O(n), Space - O(1)
     * Justification: check char one by one if they fall in the digits range in ASCII
     * Alternative with Stream: input.chars().allMatch(c -> c >= 48 && c <= 57);
     */
    public static boolean onlyDigitsAscii(String input) {
        if (input.length() != 0) {
            int i = 0;
            while (i < input.length()) {
                int ascii = (int) input.charAt(i);
                if (48 <= ascii && ascii <= 57) {
                    i++;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Big-O: Time - O(n), Space - O(1)
     * Justification: check whether this input String can be parsed into long successfully,
     *     if unsuccessful means it is not a number string, so we handle the exception by returning false
     */
    public static boolean onlyDigitsApi(String input) {
        if (input.length() == 0) {
            return false;
        } else {
            try {
                Long.parseLong(input);
                return true;
            } catch (NumberFormatException ex) {
                return false;
            }
        }
    }

    /**
     * Big-O: Time - O(n), Space - O(1)
     * Justification: check whether input matches the all-digit regex.
     */
    public static boolean onlyDigitsRegex(String input) {
        if (input.length() == 0) {
            return false;
        } else {
            return input.matches("^[0-9]*$");
        }
    }
}
