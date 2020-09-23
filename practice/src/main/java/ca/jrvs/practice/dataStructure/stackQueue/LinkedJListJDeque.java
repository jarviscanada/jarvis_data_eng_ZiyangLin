package ca.jrvs.practice.dataStructure.stackQueue;

import ca.jrvs.practice.dataStructure.list.LinkedJList;

import java.util.NoSuchElementException;

public class LinkedJListJDeque<E> implements JDeque<E> {
    int volume;
    int size;
    Node head;

    public LinkedJListJDeque(int volume) {
        this.volume = volume;
        this.size = 0;
        this.head = null;
    }

    class Node {
        E data;
        Node next;

        Node(E e) {
            data = e;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
    @Override
    public boolean enqueue(E e) {
        if (e == null) {
            throw new NullPointerException("error: cannot push null into this deque");
        }
        Node newNode = new Node(e);
        if (head == null) {
            head = newNode;
        } else {
            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
        newNode.setNext(null);
        return true;
    }

    @Override
    public E dequeue() {
        if (head == null) {
            throw new NoSuchElementException("error: this deque is empty.");
        }
        E result = head.data;
        head = head.next;
        return result;
    }

    @Override
    public void push(E e) {
        if (e == null) {
            throw new NullPointerException("error: cannot push null into this deque");
        }
        Node newNode = new Node(e);
        if (head == null) {
            head = newNode;
        } else {
            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
        newNode.setNext(null);
    }

    @Override
    public E pop() {
        if (head == null) {
            throw new NoSuchElementException("error: this deque is empty.");
        }
        Node curr = head;
        Node prev = null;
        while (curr.next != null) {
            prev = curr;
            curr = curr.next;
        }

        E result = curr.data;
        if (prev != null) {
            prev.setNext(null);
        } else {
            curr = null;
        }
        return result;
    }

    public E peek(int indicator) {
        if (indicator == 1) {
            return head.data;
        } else if (indicator == 2) {
            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            return curr.data;
        } else {
            throw new IllegalArgumentException("error: 1 for queue peek, and 2 for stack peek");
        }
    }
}
