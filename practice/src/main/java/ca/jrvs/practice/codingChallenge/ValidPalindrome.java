package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Valid-Palindrome-c353826e1ba94c218de36ae2d09dbc98
 */
public class ValidPalindrome {

    /**
     * Big-O: Time - O(n), Space - O(n)
     * Justification: first process input to contain only valid characters, then use 2 pointers
     *                from head and tail, and check for matches.
     */
    public static boolean isValidPalindrome(String input) {
        String processed = processInput(input);
        // if processed input is empty or of length 1, it is a valid palindrome
        if (input.length() == 0 || input.length() == 1) {
            return true;
        }

        // use 2 pointers to go from head and tail, once a mismatch is found, return false, O(n)
        int i = 0;
        int j = processed.length() - 1;
        while (i < processed.length()) {
            if (processed.charAt(i) != processed.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    /**
     * Big-O: Time - O(n), Space - O(n)
     * Recursion Complexity: base case: T(2) = 1, recursive step: T(n) = T(2) + T(n-2) when n > 2
     *                       T(n) = T(2) + T(n - 2) = T(2) + (1 + T(n - 4)) = T(2) + ... + T(2)
     *                            = 1 + T(n - 2) = 1 + (1 + T(n - 4)) = 1 + ...
     *                            = k + T(n - 2k) = (n/2 - 1) + T(n - 2(n/2 - 1)) = n/2 - 1 + 1 = n/2
     *                       --> T(n) = n/2 -> O(n)
     * Justification: base case 1: length = 0 or 1, then valid palindrome.
     *                base case 2: length = 2, and 2 characters equal, valid palindrome.
     *                recursive step: call function on indices input[1:length - 2]
     */
    public static boolean isValidPalindromeRecursion(String input) {
        if (input.length() == 0 || input.length() == 1) {
            return true;
        } else if (input.charAt(input.length() - 1) != input.charAt(0)) {
            return false;
        } else {
            return isValidPalindromeRecursion(input.substring(1, input.length() - 1));
        }
    }

    public static String processInput(String input) {
        // process the string to only contains lowercase letters and digits, O(n)
        input = input.trim().toLowerCase();

        // process the string to only contains letters and digits, O(n)
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if ((48 <= (int) input.charAt(i) && (int) input.charAt(i) <= 57) || (97 <= (int) input.charAt(i)
                    && (int) input.charAt(i) <= 122)) {
                temp.append(input.charAt(i));
            }
        }
        System.out.println(temp);
        return temp.toString();
    }
}
