package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Stack;

public class QueueUsingStack {

    public static class TwoStacksLinearPush {
        private Stack<Integer> stack1;
        private Stack<Integer> stack2;
        private int head;

        public TwoStacksLinearPush() {
            this.stack1 = new Stack<>();
            this.stack2 = new Stack<>();
            this.head = -1;
        }

        /**
         * Big-O: Time - O(n), Space - O(1)
         * Justification: first pop all elements from stack1 into stack2, then push the new element into
         *                stack1, and pop all elements from stack2 back to stack1.
         */
        public int enqueue(int x) {
            if (stack1.isEmpty()) {
                stack1.push(x);
                head = x;
            } else {
                head = stack1.peek();
                while (!stack1.isEmpty()) {
                    head = stack1.peek();
                    stack2.push(stack1.pop());
                }
                stack1.push(x);
                while (!stack2.isEmpty()) {
                    stack1.push(stack2.pop());
                }
            }

            return x;
        }

        /**
         * Big-O: Time - O(1), Space - O(1)
         * Justification: pop the top element in stack1 is the first element inserted into this queue.
         */
        public int dequeue() {
            int result = stack1.pop();
            if (!stack1.isEmpty()) {
                this.head = stack1.peek();
            }
            return result;
        }

        public boolean empty() {
            return stack1.isEmpty();
        }

        public int head() {
            return this.head;
        }
    }

    public static class TwoStacksConstantPush {
        private Stack<Integer> stack1;
        private Stack<Integer> stack2;
        private int head;


        public TwoStacksConstantPush() {
            this.stack1 = new Stack<>();
            this.stack2 = new Stack<>();
            this.head = -1;
        }

        /**
         * Big-O: Time - O(1), Space - O(1)
         * Justification: push the element into stack1.
         */
        public int enqueue(int x) {
            if (stack1.empty()) {
                head = x;
            }
            stack1.push(x);
            return x;
        }

        /**
         * Big-O: Time - Worst-case O(n) but amortized O(1), Space - O(1).
         *        For operations sequence enqueue(1),...,enqueue(n),dequeue(1),...,dequeue(n),
         *        the total time complexity is n (enqueue operations) + 2n (first dequeue) + (n - 1) rest dequeues,
         *        therefore the amortized average is O(2n/2n) = O(1).
         * Justification: if we push all popped elements from stack1 into stack2, stack2 will be exactly the reverse
         *                order of stack1, therefore now popping from stack2 is equivalent to dequeue for queues.
         * Amortized Analysis: average worst-case performance of each operation in a sequence of operations.
         */
        public int dequeue() {
            // transfer all data from stack1 to stack2, then the order is reversed in stack2.
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            int result = stack2.pop();

            // update head;
            if (!stack2.isEmpty()) {
                head = stack2.peek();
            }
            return result;
        }

        public boolean empty() {
            return stack1.isEmpty();
        }

        public int head() {
            return this.head;
        }
    }
}
