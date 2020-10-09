package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ticket: https://www.notion.so/Find-Largest-Smallest-d2b39840f81241dda7b13dfa02976f49
 */
public class FindLargestOrSmallest {

    /**
     * Big-O: Time complexity O(n), space complexity O(1)
     * Justification: set smallest/largest to a default number, and update by comparison with each number in nums.
     */
    public static int findMaxOrMinLoop(int[] nums, String operator) {
        if (operator.equals("Max")) {
            int largest = -1;
            for (int num : nums) {
                if (num > largest) {
                    largest = num;
                }
            }

            return largest;
        } else {
            int smallest = 999;
            for (int num : nums) {
                if (num < smallest) {
                    smallest = num;
                }
            }

            return smallest;
        }
    }

    /**
     * Big-O: Time complexity O(n), space complexity O(1)
     * Justification: use Java 8 Stream API - Arrays.stream()
     */
    public static int findMaxOrMinStream(int[] nums, String operator) {
        if (operator.equals("Max")) {
            return Arrays.stream(nums).max().getAsInt();
        } else {
            return Arrays.stream(nums).min().getAsInt();
        }
    }

    /**
     * Big-O: Time complexity O(n), space complexity O(n)
     * Justification: use Java Collections API - Collections.max()
     */
    public static int findMaxOrMinApi(int[] nums, String operator) {
        if (operator.equals("Max")) {
            // Collections.max() only takes wrapper array, so we need to convert from primitive
            //     array to wrapper array first.
            Integer[] numList = Arrays.stream(nums).boxed().toArray(Integer[]::new);
            return Collections.max(Arrays.asList(numList));
        } else {
            Integer[] numList = Arrays.stream(nums).boxed().toArray(Integer[]::new);
            return Collections.min(Arrays.asList(numList));
        }
    }
}
