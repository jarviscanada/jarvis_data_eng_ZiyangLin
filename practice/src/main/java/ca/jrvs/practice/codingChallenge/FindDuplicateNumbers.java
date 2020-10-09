package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * ticket: https://www.notion.so/Find-the-Duplicate-Number-98054c5db9204482854983edbb5cc49f
 */
public class FindDuplicateNumbers {

    /**
     * Big-O: Time complexity O(nlog(n)) for sorting, space complexity O(1)
     * Justification: if the array is sorted, then we can simply use an incrementer to check elements one by
     *                one. Once a mismatch is found at index i, then it means number i - 1 appears twice.
     */
    public static int findDuplicateSort(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i - 1;
            }
        }

        return -1;
    }

    /**
     * Big-O: Time complexity O(n), space complexity O(n)
     * Justification: populate the set for each number in nums, once a number that already exists in
     *                the set is found, simply return that number.
     */
    public static int findDuplicateSet(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            if (!numSet.contains(num)) {
                numSet.add(num);
            } else {
                return num;
            }
        }

        return -1;
    }
}
