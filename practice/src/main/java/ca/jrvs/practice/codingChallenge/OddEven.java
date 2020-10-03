package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Sample-Check-if-a-number-is-even-or-odd-7e1c04b0aec545b695f42171576f23c3
 */
public class OddEven {

    /**
     * Big-O: O(1)
     * Justification: arithmetic operation.
     */
    public static String oddEvenMod(int i) {
        if (i % 2 == 0) {
            return "the integer is even.";
        } else {
            return "the integer is odd.";
        }
    }

    /**
     * Big-O: O(1)
     * Justification: bitwise operation using the AND operator.
     * e.g. 0100 (4) & 0001 (1) = 0000 (0) -> even, and 0101 (5) & 0001 (1) = 0001 (1) -> odd
     */
    public static String oddEvenBit(int i) {
        if ((i & 1) == 1) {
            return "the integer is odd.";
        } else {
            return "the integer is even.";
        }
    }
}
