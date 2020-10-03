package ca.jrvs.practice.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort {

    public static void mergeSort(int[] arr, int n) {
        // n >= 2 means that arr.length > 1, so it is not breaking condition.
        if (n >= 2) {
            // set up mid point and construct 2 temp arrays for merging.
            int mid = n / 2;
            int[] left = new int[mid];
            int[] right = new int[n - mid];
            System.arraycopy(arr, 0, left, 0, mid);
            if (n - mid >= 0) System.arraycopy(arr, mid, right, 0, n - mid);

            // recursive calls and merges.
            mergeSort(left, mid);
            mergeSort(right, n - mid);
            merge(arr, left, right);
        }
    }

    private static void merge(int[] arr, int[] left, int[] right) {
        // merge the 2 arrays
        int i = 0;
        int j = 0;
        int count = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[count] = left[i];
                i++;
            } else {
                arr[count] = right[j];
                j++;
            }
            count++;
        }

        // copying remaining elements in.
        while (i < left.length) {
            arr[count] = left[i];
            i++;
            count++;
        }
        while (j < right.length) {
            arr[count] = right[j];
            j++;
            count++;
        }
    }
}
