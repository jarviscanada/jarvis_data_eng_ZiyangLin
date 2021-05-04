package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.dataStructure.list.LinkedJList;

/**
 * ticket: https://www.notion.so/Reverse-Linked-List-ea3ace86263840e292a1932c216ae204
 */
public class ReverseLinkedList {


    /**
     * Big-O: Time complexity O(n), space complexity O(1)
     * Justification: use 2 pointers prev and curr, then change curr's next to prev while extracting
     *                the original curr.next for updating, keep doing it until reached the end.
     */
    public static LinkedJList<Integer>.Node reverseIterative(LinkedJList<Integer>.Node head) {
        if (head != null) {
            LinkedJList<Integer>.Node curr1 = null;
            LinkedJList<Integer>.Node curr2 = head;
            while (curr2 != null) {
                LinkedJList<Integer>.Node temp = curr2.getNext();
                curr2.setNext(curr1);
                curr1 = curr2;
                curr2 = temp;
            }
            return curr1;
        }

        return null;
    }

    /**
     * Big-O: Time complexity O(n), space complexity O(n)
     * Justification: n1 -> n2 -> ... -> nk -> n(k + 1) <- n(k + 2) <- ... <- nm <- null
     *                suppose we are at nk, then we must have nk.next.next = nk
     *                notice the base case for this algorithm.
     */
    public static LinkedJList<Integer>.Node reverseRecursive(LinkedJList<Integer>.Node head) {
        if (head == null || head.getNext() == null) {
            return head;
        } else {
            LinkedJList<Integer>.Node temp = reverseRecursive(head.getNext());
            head.getNext().setNext(head);
            head.setNext(null);
            return temp;
        }
    }
}
