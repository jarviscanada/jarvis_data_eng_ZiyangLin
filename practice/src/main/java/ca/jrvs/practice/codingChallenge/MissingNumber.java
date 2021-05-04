package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * ticket: https://www.notion.so/Missing-Number-781b1ecc8f0d4762bfa3d2cf58640f41
 */
public class MissingNumber {

    /**
     * Big-O: Time complexity O(nlog(n)) for sorting, space complexity O(1)
     * Justification: if the array is sorted, then we can simply use an incrementer to check elements one by
     *                one. Once a mismatch is found, that's the missing number.
     */
    public static int missingNumberSort(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Big-O: Time complexity O(n), space complexity O(n)
     * Justification: first pass, use a set to record existence of all numbers in nums;
     *                second pass, check which number is missing in the step from 0 to n.
     */
    public static int missingNumberSet(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int largest = nums.length;
        for (int i = 0; i < largest; i++) {
            if (!numSet.contains(i)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Big-O: Time complexity O(n), space complexity O(1)
     * Justification: calculate the expected sum if there is no missing number by arithmetic sequence
     *                sum formula ((an+a0)*(n+1))/2 in range [0, n]. Missing number = expectedSum - actualSum.
     */
    public static int missingNumberGauss(int[] nums) {
        int theoreticalSum = ((nums.length + 1) * nums.length) / 2;
        int actualSum = 0;
        for (int num : nums) {
            actualSum += num;
        }

        return theoreticalSum - actualSum;
    }
}
