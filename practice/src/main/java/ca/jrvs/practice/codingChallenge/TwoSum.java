package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Map;

/**
 * ticket: https://www.notion.so/Two-Sum-9fd42023bdba4dc1997fa87d6a2e7328
 */
public class TwoSum {

    /**
     * Big-O: Time - O(n^2), Space - O(1)
     * Justification: nested loop solution that check all possible pairs in the array.
     */
    public static int[] twoSumBruteForce(int[] nums, int target) {

        // brute force solution with 2 loops, give O(n^2) complexity.
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * Big-O: Time - O(n), Space - O(1)
     * Justification: for a sorted list, we use 2 pointers from start and end of the array, if they sum
     *                to target then return; if they sum less than target, then move the first pointer
     *                towards the end; if they sum larger than target, then move the second pointer
     *                towards the start.
     */
    public static int[] twoSumSorted(int[] nums, int target) {
        // [1, 2, 3, 6, 11, 13], 7
        int i = 0;
        int j = nums.length - 1;
        while (i < nums.length && j >= 0) {
            if (nums[i] + nums[j] == target) {
                return new int[]{i, j};
            } else if (nums[i] + nums[j] < target) {
                i++;
            } else {
                j--;
            }
        }

        return new int[]{-1, -1};
    }

    /**
     * Big-O: Time - O(n), Space - O(n)
     * Justification: for all number in nums, store the difference between target and that number as keys,
     *                and the number's index as values in a HashMap. Then in second pass, check for each number
     *                if it is in the HashMap as a key.
     */
    public static int[] twoSumHashMap(int[] nums, int target) {
        Map<Integer, Integer> sumMap = new HashMap<>();

        // first loop, record target - nums[i] for all i in nums as keys, store indices as values.
        for (int i = 0; i < nums.length; i++) {
            if (!sumMap.containsKey(target - nums[i])) {
                sumMap.put(target - nums[i], i);
            }
        }

        // check if nums[i] as key for all i in nums, if there is, that means i + sumMap.get(nums[i]) = target.
        for (int i = 0; i < nums.length; i++) {
            if (sumMap.containsKey(nums[i])) {
                return new int[]{i, sumMap.get(nums[i])};
            }
        }

        return new int[]{-1, -1};
    }
}
