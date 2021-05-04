package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Merge-Sorted-Array-ab81aa1ae0c741b88882acf6f37ae518
 */
public class MergeSortedArray {

    /**
     * Big-O: O(n + m) time complexity, O(n) space complexity
     * Justification: get a copy of dest, and compare the element from left to right one by one
     *                with that copy and src, put the smaller one onto the next slot of dest.
     */
    public static void mergeSortedArray(int[] dest, int[] src) {
        // create a temporary array to store dest elements -> O(n)
        int[] temp = new int[dest.length - src.length];
        if (temp.length >= 0) System.arraycopy(dest, 0, temp, 0, temp.length);

        int j = 0;
        int k = 0;
        // compare elements from left to right one by one with temp and src, and always put
        //     the smaller one onto the next slot of dest -> O(n + m)
        for (int i = 0; i < dest.length; i++) {
            if (j == temp.length) {
                dest[i] = src[k];
                k++;
            } else if (k == src.length) {
                dest[i] = temp[j];
                j++;
            } else {
                if (temp[j] < src[k]) {
                    dest[i] = temp[j];
                    j++;
                } else {
                    dest[i] = src[k];
                    k++;
                }
            }
        }

    }

}
