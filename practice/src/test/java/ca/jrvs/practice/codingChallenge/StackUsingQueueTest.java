package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class StackUsingQueueTest {

    @Test
    public void twoQueuesStackOne() {
        StackUsingQueue.TwoQueuesStackOne stack = new StackUsingQueue.TwoQueuesStackOne();
        stack.push(2);
        stack.push(4);
        stack.push(6);
        stack.push(8);
        assertEquals(8, stack.pop());
        assertEquals(6, stack.top());
        assertEquals(6, stack.pop());
        assertEquals(4, stack.pop());
        assertEquals(2, stack.pop());
        assertTrue(stack.empty());
    }

    @Test
    public void twoQueuesStackTwo() {
        StackUsingQueue.TwoQueuesStackTwo stack = new StackUsingQueue.TwoQueuesStackTwo();
        stack.push(2);
        stack.push(4);
        stack.push(6);
        stack.push(8);
        assertEquals(8, stack.pop());
        assertEquals(6, stack.top());
        assertEquals(6, stack.pop());
        assertEquals(4, stack.pop());
        assertEquals(2, stack.pop());
        assertTrue(stack.empty());
    }

    @Test
    public void oneQueueStack() {
        StackUsingQueue.OneQueueStack stack = new StackUsingQueue.OneQueueStack();
        stack.push(2);
        stack.push(4);
        stack.push(6);
        stack.push(8);
        assertEquals(8, stack.pop());
        assertEquals(6, stack.top());
        assertEquals(6, stack.pop());
        assertEquals(4, stack.pop());
        assertEquals(2, stack.pop());
        assertTrue(stack.empty());
    }
}