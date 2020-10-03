package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.dataStructure.list.LinkedJList;

import java.util.HashMap;

/**
 * ticket: https://www.notion.so/LinkedList-Cycle-7ad68227762a457bb30fa66dab56ad24
 */
public class LinkedListCycle {

    /**
     * Big-O: Time - O(n), Space - O(1)
     * Justification: this solution based on the fact that, if a cycle exists, a fast pointer will always meet
     *                the slow pointer.
     */
    public static boolean hasCycle(LinkedJList<Integer> list) {
        // if list is empty or the only node has a null next pointer, the linked list can't have a cycle
        if (list.getHead() == null || list.getHead().getNext() == null) {
            return false;
        }

        // use two pointers (one forward 1 index at a time while another forward 2), then if there is a cycle,
        //     they are guaranteed to meet each other.
        LinkedJList<Integer>.Node curr1 = list.getHead();
        LinkedJList<Integer>.Node curr2 = list.getHead().getNext();
        while (!curr1.equals(curr2)) {
            // if list has no cycle, then curr2 will eventually get to null.
            if (curr2 == null || curr2.getNext() == null) {
                return false;
            }

            curr1 = curr1.getNext();
            curr2 = curr2.getNext().getNext();
        }

        return true;

    }

    /**
     * Big-O: Time - O(n), Space - O(n)
     * Justification: this solution records visited node in a HashMap, and return when a duplicate is found.
     */
    public static boolean hasCycleHashMap(LinkedJList<Integer> list) {
        // if list is empty or the only node has a null next pointer, the linked list can't have a cycle
        if (list.getHead() == null || list.getHead().getNext() == null) {
            return false;
        }

        // use a hash map to record nodes that are already visited
        LinkedJList<Integer>.Node curr = list.getHead();
        HashMap<LinkedJList<Integer>.Node, Boolean> checkMap = new HashMap<>();
        while (curr != null) {
            if (checkMap.containsKey(curr)) {
                return true;
            } else {
                checkMap.put(curr, true);
            }
            curr = curr.getNext();
        }

        return false;
    }
}
