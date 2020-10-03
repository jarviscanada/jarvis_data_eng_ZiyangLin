package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/LinkedList-Cycle-7ad68227762a457bb30fa66dab56ad24
 */
public class FibonacciNumbers {

    /**
     * Big-O: Time - O(n), Space - O(1)
     * Justification: recursive solution: base case when n < 2, fib(n) = n, recursive step follows the
     *                formula fib(n) = fib(n - 1) + fib(n - 2)
     */
    public static int fibonacciRecursion(int input) {
        // if n < 2, fib(n) = n: fib(0) = 0, fib(1) = 1, fib(2) = 1
        if (input < 2) {
            return input;
        } else {
            // fib(n) = fib(n - 1) + fib(n - 2)
            return fibonacciRecursion(input - 1) + fibonacciRecursion(input - 2);
        }
    }

    /**
     * Big-O: Time - O(n), Space - O(1)
     * Justification: dynamic programming solution: build up an array [fib(0), fib(1), ..., fib(n)],
     *                then return the last element as result.
     */
    public static int fibonacciDP(int input) {
        // if n < 2, fib(n) = n: fib(0) = 0, fib(1) = 1, fib(2) = 1
        if (input < 2) {
            return input;
        }

        // length of the sequence = n + 1 since fib(n) starts from 0
        int[] dp = new int[input + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < input + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[input];
    }

}
