package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Swap-two-numbers-d75f246f43064b7e9b94e962d8f4e99c
 */
public class SwapTwoNumbers {

    /**
     * Big-O: Time complexity O(1), space complexity O(1)
     * Justification: bitwise XOR swap algorithm X = X ^ Y, Y = Y ^ X, X = X ^ Y
     *                example X = 1010, Y = 0011, X = X ^ Y = 1001 -> Y = Y ^ X = 1010, X = X ^ Y = 0011
     * Precondition: input length 2
     * Requirement: do not use a third variable
     */
    public static void swapBit(int[] input) {
        input[0] = input[0] ^ input[1];
        input[1] = input[1] ^ input[0];
        input[0] = input[0] ^ input[1];
    }

    /**
     * Big-O:Time complexity O(1), space complexity O(1)
     * Justification: x = x + y -> y = y - x = (x + y) - y = x -> x = x - y = (x + y) - x = y
     * Precondition: input length 2
     * Requirement: do not use a third variable
     */
    public static void swapArithmetic(int[] input) {
        input[0] = input[0] + input[1];
        input[1] = input[0] - input[1];
        input[0] = input[0] - input[1];
    }

}
