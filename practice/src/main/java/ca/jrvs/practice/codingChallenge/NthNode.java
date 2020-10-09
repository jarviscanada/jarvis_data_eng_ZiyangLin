package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.dataStructure.list.LinkedJList;

/**
 * ticket: https://www.notion.so/Nth-Node-From-End-of-LinkedList-db54f1676b8f4b729d6e26fc91029c53
 */
public class NthNode {

    /**
     * Big-O: Time - O(n), Space - O(1)
     * Justification: this problem is the same as removing the (L - n + 1)th node in the list.
     *     So we first figure out the size of list with 1 pass, and then direct to the (L - n + 1)th node to remove.
     */
    public static int removeNthNode(LinkedJList<Integer> list, int n) {
        // 1 pass through the list to get size -> O(n)
        int size = list.size();
        int index = 0;

        // get to the (L - n + 1)th node (i.e. index L - n).
        LinkedJList<Integer>.Node curr =  list.getHead();
        LinkedJList<Integer>.Node prev =  null;
        while (index != size - n) {
            prev = curr;
            curr = curr.getNext();
            index++;
        }
        int result = curr.getData();
        if (prev != null) {
            prev.setNext(curr.getNext());
        } else {
            throw new RuntimeException("error: list is empty or n > list.size()");
        }
        return result;
    }
}
