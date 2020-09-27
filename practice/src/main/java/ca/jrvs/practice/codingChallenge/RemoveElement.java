package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Remove-Element-325f817dde874c0a998d72e53a5a8f57
 */
public class RemoveElement {

    /**
     * Big-O: Time - O(n); Space - O(1)
     * Justification: solution using 2 pointers, see descriptions below
     */
    public static int removeElement(int[] input, int val) {
        int i = 0;

        for (int num : input) {
            if (num != val) {
                // record the non-matching number in array at the i index
                input[i] = num;
                i++;
            }
            // all matched numbers are discarded
        }

        // so the value i will be the number of values get recorded in the array, and therefore the length
        return i;
    }
    
}
