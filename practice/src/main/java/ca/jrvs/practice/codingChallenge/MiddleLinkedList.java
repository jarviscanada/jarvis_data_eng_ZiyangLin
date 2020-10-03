package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.dataStructure.list.LinkedJList;

/**
 * ticket: https://www.notion.so/Middle-of-the-Linked-List-07d93d9b42f64d9f8cab4e835b7ed5bd
 */
public class MiddleLinkedList {

    /**
     * Big-O: Time - O(n), Space - O(1)
     * Justification: two pointers, one forward 1 index at a time while another forward 2 index.
     *     When pointer 2's next next pointer is null, pointer 1 is right at the middle.
     */
    public static int middleLinkedList(LinkedJList<Integer> list) {
        // initialize 2 pointers as head.
        LinkedJList<Integer>.Node curr1 = list.getHead();
        LinkedJList<Integer>.Node curr2 = list.getHead();

        // one half-pass, which takes about O(n/2)
        // and && is used here to prevent NullPointerException
        while (curr2.getNext() != null && curr2.getNext().getNext() != null) {
            curr1 = curr1.getNext();
            curr2 = curr2.getNext().getNext();
        }

        // get the size, which takes O(n)
        if (list.size() % 2 == 0) {
            return curr1.getNext().getData();
        } else {
            return curr1.getData();
        }
    }
}
