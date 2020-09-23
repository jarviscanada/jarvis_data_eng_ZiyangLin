package ca.jrvs.practice.dataStructure.stackQueue;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedJListJDequeTest {

    @Test
    public void enqueue() {
        JDeque<String> deque1 = new LinkedJListJDeque<>(10);
        deque1.enqueue("1");
        deque1.enqueue("2");
        deque1.enqueue("3");
        deque1.enqueue("4");
        assertEquals("1", deque1.dequeue());
        assertEquals("2", deque1.dequeue());
    }

    @Test
    public void dequeue() {
        JDeque<String> deque1 = new LinkedJListJDeque<>(10);
        deque1.enqueue("1");
        deque1.enqueue("2");
        deque1.enqueue("3");
        deque1.enqueue("4");
        assertEquals("1", deque1.dequeue());
        assertEquals("2", deque1.dequeue());
    }

    @Test
    public void push() {
        JDeque<String> deque1 = new LinkedJListJDeque<>(10);
        deque1.push("1");
        deque1.push("2");
        deque1.push("3");
        deque1.push("4");
        assertEquals("4", deque1.pop());
        assertEquals("3", deque1.pop());
    }

    @Test
    public void pop() {
        JDeque<String> deque1 = new LinkedJListJDeque<>(10);
        deque1.push("1");
        deque1.push("2");
        deque1.push("3");
        deque1.push("4");
        assertEquals("4", deque1.pop());
        assertEquals("3", deque1.pop());
    }

    @Test
    public void peek() {
        JDeque<String> deque1 = new LinkedJListJDeque<>(10);
        deque1.push("1");
        deque1.push("2");
        deque1.push("3");
        deque1.push("4");
        assertEquals("1", deque1.peek(1));
        assertEquals("4", deque1.peek(2));
    }
}