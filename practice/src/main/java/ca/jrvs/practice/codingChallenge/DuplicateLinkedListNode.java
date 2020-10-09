package ca.jrvs.practice.codingChallenge;


import ca.jrvs.practice.dataStructure.list.LinkedJList;

import java.util.HashMap;

/**
 * ticket: https://www.notion.so/Duplicate-LinkedList-Node-85ab8362e8564d58a442b7ed73a81252
 */
public class DuplicateLinkedListNode {

    /**
     * Big-O: Time - O(n^2), Space - O(n)
     * Justification: the algorithm first record the count for all nodes in first pass, then remove those
     *                that have count > 1 in second pass.
     */
    public static void removeDuplicates(LinkedJList<Integer> list) {
        HashMap<Integer, Integer> countMap = new HashMap<>();

        // first pass, iterate over all nodes to record counts
        LinkedJList<Integer>.Node curr = list.getHead();
        while (curr != null) {
            if (countMap.containsKey(curr.getData())) {
                countMap.replace(curr.getData(), countMap.get(curr.getData()) + 1);
            } else {
                countMap.put(curr.getData(), 1);
            }
            curr = curr.getNext();
        }

        // second pass, remove all duplicates data so that all counts are reduced to 1
        // note that here we used the remove(int index) method from LinkedJList, which is O(n).
        //     However, if we implement another O(1) method remove(Node node), the algorithm can be faster.
        curr = list.getHead();
        int index = 0;
        while (curr != null) {
            if (countMap.get(curr.getData()) > 1) {
                list.remove(index);
                countMap.replace(curr.getData(), countMap.get(curr.getData()) - 1);
            }

            curr = curr.getNext();
            index++;
        }

    }
}
