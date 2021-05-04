package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Count-Primes-668a1d454c5a4240bfaf8b28cbaf2486
 */
public class CountPrimes {

    /**
     * Big-O: Time complexity O(n*log(log(n))), space complexity O(n)
     * Justification: uses the Sieve of Eratosthenes method, it states that for all natural number k,
     *                starting at k^2, all increments k^2 + k, k^2 + 2k, ... are all non-primes
     */
    public static int countPrimes(int n) {
        // temporary storing a table for primes or non-primes as boolean.
        boolean[] isPrime = new boolean[n];
        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }

        // we set the loop to terminate at i = Math.sqrt(n) to avoid redundant counts. Consider
        //      the factor of 12, 2 * 6, 3 * 4, 4 * 3, 6 * 2, the latter 2 are obviously unnecessary.
        //      The boundary is at sqrt(n) since sqrt(n)^2 = n.
        for (int i = 2; i < Math.sqrt(n); i++) {
            // if a number is marked as prime, meaning that it has not been examined yet
            if (isPrime[i]) {
                // here is the Sieve of Eratosthenes to mark all k^2 + nk as non-primes up to n.
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        // loop through the table to count how many is left.
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (isPrime[i]) {
                count++;
            }
        }

        return count;
    }

}
