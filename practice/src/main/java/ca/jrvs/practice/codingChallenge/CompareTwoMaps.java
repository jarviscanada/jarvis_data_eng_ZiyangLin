package ca.jrvs.practice.codingChallenge;

import java.util.Map;

/**
 * ticket: https://www.notion.so/How-to-compare-two-maps-236e592f55c2483dbb204e2ec13c8e98
 */
public class CompareTwoMaps {

    /**
     * Big-O: Time complexity O(n), space complexity O(n)
     * Justification: 2 maps are equal iff they have all same keys, and all keys have all same values
     */
    public static <K,V> boolean compareMaps(Map<K,V> m1, Map<K,V> m2) {
        // since keys are unique, so if number of key does not match, 2 Maps cannot be equal.
        if (m1.keySet().size() != m2.keySet().size()) {
            return false;
        }

        // compare each key and value pair.
        for (K key : m1.keySet()) {
            if (!m2.containsKey(key) || !m1.get(key).equals(m2.get(key))) {
                return false;
            }
        }

        return true;
    }

}
