package ca.jrvs.practice.codingChallenge;

import java.lang.reflect.Array;
import java.util.*;

/**
 * ticket: https://www.notion.so/Contains-Duplicate-3134b69de70a468bbe719bab42c48180
 */
public class ContainsDuplicate {

    /**
     * Big-O: Time complexity O(nlog(n)) for sorting, space complexity O(1)
     * Justification: for sorted array, if 2 adjacent elements are the same, then they are duplicates.
     */
    public static boolean containsDuplicateSort(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }

        return false;
    }

    /**
     * Big-O: Time complexity O(n), space complexity O(n)
     * Justification: populate the set for each number in nums, once a number that already exists in
     *                the set is found, then there are duplicates in nums.
     */
    public static boolean containsDuplicateSet(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            if (!numSet.contains(num)) {
                numSet.add(num);
            } else {
                return true;
            }
        }

        return false;
    }
}
