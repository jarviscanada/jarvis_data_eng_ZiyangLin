package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Duplicates-from-Sorted-Array-ef0ba3c020fb416d8676d9613501c183
 */
public class DuplicatesFromSortedArray {

    /**
     * Big-O: Time complexity O(n), space complexity O(1)
     * Justification: use 2 pointers, one keep track of unique elements, the other goes faster to
     *                find a different element.
     * Requirement: removing duplicates in-place, without initializing another array
     */
    public static int removeDuplicates(int[] input) {
        int i = 0;
        int j = 1;
        while (j < input.length) {
            if (input[j] == input[i]) {
                j++;
            } else {
                input[i + 1] = input[j];
                i++;
            }
        }

        // mark all remaining slots for original input as -1.
        for (int k = i + 1; k < input.length; k++) {
            input[k] = -1;
        }
        return i + 1;
    }
}
