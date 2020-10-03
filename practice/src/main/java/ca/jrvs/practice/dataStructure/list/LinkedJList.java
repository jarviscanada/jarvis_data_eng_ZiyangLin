package ca.jrvs.practice.dataStructure.list;

import java.util.Objects;

public class LinkedJList<E> implements JList<E> {
    Node head;


    public class Node {
        E data;
        Node next;

        Node(E e) {
            data = e;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getNext() {
            return next;
        }

        public E getData() {
            return data;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(data, node.data);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data);
        }
    }

    public Node getHead() {
        return head;
    }

    @Override
    public boolean add(E e) {
        // check if the adding element is null.
        if (e == null) {
            throw new NullPointerException("error: cannot add null object to ArrayJList.");
        }

        // create a node for the new object
        Node newNode = new Node(e);
        newNode.setNext(null);

        // set this new node as the first element if this list is empty.
        if (head == null) {
            head = newNode;
        } else {
            // iterate to the last object of the linked list, and set the next
            //     pointer of that object to be newNode.
            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.setNext(newNode);
        }

        return true;
    }

    @Override
    public Object[] toArray() {
        if (head == null) {
            return null;
        }

        JList<Object> result = new ArrayJList<>();
        Node curr = head;
        while (curr != null) {
            result.add(curr.data);
            curr = curr.next;
        }

        return result.toArray();
    }

    @Override
    public int size() {
        int size = 0;
        if (head == null) {
            return 0;
        }

        Node curr = head;
        while (curr != null) {
            size++;
            curr = curr.next;
        }

        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int indexOf(Object o) {
        if (head == null) {
            return -1;
        }

        int result = 0;
        Node curr = head;
        while (curr.next != null) {
            if (curr.data.equals(o)) {
                return result;
            }
            result++;
            curr = curr.next;
        }

        return -1;
    }

    @Override
    public boolean contains(Object o) {
        // check if the adding element is null.
        if (o == null) {
            throw new NullPointerException("error: cannot check presence of a null object in an LinkedJList.");
        }

        return indexOf(o) != -1;
    }

    @Override
    public E get(int index) {
        if (head == null) {
            throw new IndexOutOfBoundsException("error: this LinkedJList is currently empty.");
        }
        if (index >= size()) {
            throw new IndexOutOfBoundsException("error: index out of bound.");
        }

        int i = 0;
        Node curr = head;
        while (curr != null) {
            if (i == index) {
                return curr.data;
            }
            i++;
            curr = curr.next;
        }

        return null;
    }

    @Override
    public E remove(int index) {
        if (head == null) {
            throw new IndexOutOfBoundsException("error: this LinkedJList is currently empty.");
        }
        if (index >= size()) {
            throw new IndexOutOfBoundsException("error: index out of bound.");
        }
        int i = 0;
        Node prev = null;
        Node curr = head;
        while (index != i) {
            i++;
            prev = curr;
            curr = curr.next;
        }

        if (prev != null) {
            prev.setNext(curr.next);
        } else {
            head = null;
        }

        return curr.data;
    }

    @Override
    public void clear() {
        head = null;
    }
}
