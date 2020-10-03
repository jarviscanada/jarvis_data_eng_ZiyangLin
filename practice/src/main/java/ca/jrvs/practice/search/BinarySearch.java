package ca.jrvs.practice.search;

import java.util.Optional;

public class BinarySearch {

    /**
     * find the the target index in a sorted array
     *
     * @param arr input array is sorted
     * @param target value to be searched
     * @return target index or Optional.empty() if not found
     */
    public static <E> Optional<Integer> binarySearchRecursion(int[] arr, int target, int leftEnd, int rightEnd) {
        if (leftEnd < rightEnd && leftEnd > arr.length - 1) {
            return Optional.empty();
        } else {
            int midPoint = leftEnd + (rightEnd - leftEnd) / 2;
            if (arr[midPoint] == target) {
                return Optional.of(midPoint);
            } else if (arr[midPoint] < target) {
                return binarySearchRecursion(arr, target, midPoint + 1, rightEnd);
            } else {
                return binarySearchRecursion(arr, target, leftEnd, midPoint - 1);
            }
        }
    }

    /**
     * find the the target index in a sorted array
     *
     * @param arr input array is sorted
     * @param target value to be searched
     * @return target index or Optional.empty() if not found
     */
    public static <E> Optional<Integer> binarySearchIteration(int[] arr, int target) {
        int i = 0;
        int j = arr.length - 1;
        while (i <= j) {
            int midPoint = i + (j - 1) / 2;
            if (arr[midPoint] == target) {
                return Optional.of(midPoint);
            } else if (arr[midPoint] < target) {
                // if current mid point item is less than target, ignore left half.
                i = midPoint + 1;
            } else {
                // if current mid point item is larger than target, ignore right half.
                j = midPoint - 1;
            }
        }

        return Optional.empty();
    }
}