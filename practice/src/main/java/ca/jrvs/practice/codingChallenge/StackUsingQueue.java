package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ticket: https://www.notion.so/Implement-Stack-using-Queue-e88283b19f1f4a78b104e164c44b3f0a
 */
public class StackUsingQueue {

    public static class TwoQueuesStackOne {
        // queue1 for storing data, queue2 for temporary storage for removed elements
        private Queue<Integer> queue1;
        private Queue<Integer> queue2;
        private int top;

        public TwoQueuesStackOne() {
            this.queue1 = new LinkedList<>();
            this.queue2 = new LinkedList<>();
            this.top = -1;
        }

        /**
         * Big-O: Time - O(1) as LinkedList.add(), Space - O(1)
         * Justification: simply add the element to queue1 which stores actual data.
         */
        public int push(int x) {
            queue1.add(x);
            this.top = x;
            return x;
        }

        /**
         * Big-O: Time - O(n), Space - O(1)
         * Justification: temporarily store all elements before the last inserted one into queue2,
         *                and then dequeue the only remaining one, then swap queue1 and queue2.
         */
        public int pop() {
            // dequeue all elements before the last inserted one.
            while (queue1.size() > 1) {
                this.top = queue1.remove();
                queue2.add(this.top);
            }

            // swap the 2 queues.
            int lastInElement = queue1.remove();
            Queue<Integer> temp = queue1;
            queue1 = queue2;
            queue2 = temp;
            return lastInElement;
        }

        /**
         * Big-O: Time - O(1), Space - O(1)
         * Justification: simple LinkedList.isEmpty() operation.
         */
        public boolean empty() {
            return queue1.isEmpty();
        }

        public int top() {
            return top;
        }
    }

    public static class TwoQueuesStackTwo {
        // queue1 for storing data, queue2 for temporary storage for removed elements
        private Queue<Integer> queue1;
        private Queue<Integer> queue2;
        private int top;

        public TwoQueuesStackTwo() {
            this.queue1 = new LinkedList<>();
            this.queue2 = new LinkedList<>();
            this.top = -1;
        }

        /**
         * Big-O: Time - O(n), Space - O(1)
         * Justification: first add the element into the empty queue2, then move all previously inserted
         *                elements in queue2 in order. Finally, swap queue1 and queue2.
         */
        public int push(int x) {
            // first add the element into queue2,
            //     then move all elements previously inserted into queue2 in order.
            queue2.add(x);
            top = x;
            while (!queue1.isEmpty()) {
                queue2.add(queue1.remove());
            }

            // swap queue1 and queue2
            Queue<Integer> temp = queue2;
            queue2 = queue1;
            queue1 = temp;

            return x;
        }

        /**
         * Big-O: Time - O(n), Space - O(1)
         * Justification: with the push() method, queue1 has the inverted order of the actual stack,
         *                therefore a simply dequeue will do the trick. Also update top.
         */
        public int pop() {
            // pop current top element, and update top
            int removed = queue1.remove();
            if (!queue1.isEmpty()) {
                top = queue1.peek();
            }
            return removed;
        }

        /**
         * Big-O: Time - O(1), Space - O(1)
         * Justification: simple LinkedList.isEmpty() operation.
         */
        public boolean empty() {
            return queue1.isEmpty();
        }

        public int top() {
            return top;
        }
    }

    public static class OneQueueStack {
        private Queue<Integer> queue;
        private int top;

        public OneQueueStack() {
            this.queue = new LinkedList<>();
            this.top = -1;
        }

        /**
         * Big-O: Time - O(n), Space - O(1)
         * Justification: first add the element into the queue, then swap all elements one by one
         *                from the bottom of the queue to the top one by one, so the stack structure
         *                is achieved.
         */
        public int push(int x) {
            queue.add(x);
            this.top = x;
            int size = queue.size();
            while (size > 1) {
                queue.add(queue.remove());
                size--;
            }

            return x;
        }

        /**
         * Big-O: Time - O(1), Space - O(1)
         * Justification: usual queue.dequeue().
         */
        public int pop() {
            int result = queue.remove();
            if (!queue.isEmpty()) {
                this.top = queue.peek();
            }
            return result;
        }

        public boolean empty() {
            return queue.isEmpty();
        }

        public int top() {
            return this.top;
        }
    }
}
