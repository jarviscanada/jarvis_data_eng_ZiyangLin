package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class QueueUsingStackTest {

    @Test
    public void twoStacksLinearPush() {
        QueueUsingStack.TwoStacksLinearPush queue = new QueueUsingStack.TwoStacksLinearPush();
        queue.enqueue(2);
        queue.enqueue(4);
        queue.enqueue(6);
        queue.enqueue(8);
        assertEquals(2, queue.dequeue());
        assertEquals(4, queue.head());
        assertEquals(4, queue.dequeue());
        assertEquals(6, queue.dequeue());
        assertEquals(8, queue.dequeue());
        assertTrue(queue.empty());
    }

    @Test
    public void twoStacksConstantPush() {
        QueueUsingStack.TwoStacksConstantPush queue = new QueueUsingStack.TwoStacksConstantPush();
        queue.enqueue(2);
        queue.enqueue(4);
        queue.enqueue(6);
        queue.enqueue(8);
        assertEquals(2, queue.dequeue());
        assertEquals(4, queue.head());
        assertEquals(4, queue.dequeue());
        assertEquals(6, queue.dequeue());
        assertEquals(8, queue.dequeue());
        assertTrue(queue.empty());
    }
}