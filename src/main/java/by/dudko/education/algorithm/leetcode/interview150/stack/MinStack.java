package by.dudko.education.algorithm.leetcode.interview150.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/min-stack/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 155. Min Stack
 * <p>
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * <p>
 * Implement the MinStack class:
 * MinStack() initializes the stack object.
 * void push(int val) pushes the element val onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
 * You must implement a solution with O(1) time complexity for each function.
 * <p>
 * Example 1:
 * Input
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * Output
 * [null,null,null,null,-3,null,0,-2]
 * Explanation
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin(); // return -3
 * minStack.pop();
 * minStack.top();    // return 0
 * minStack.getMin(); // return -2
 * <p>
 * Constraints:
 * -2^31 <= val <= 2^31 - 1
 * Methods pop, top and getMin operations will always be called on non-empty stacks.
 * At most 3 * 104 calls will be made to push, pop, top, and getMin.
 */
public class MinStack {
    private final Deque<Integer> minimums = new ArrayDeque<>();
    private final Deque<Integer> data = new ArrayDeque<>();

    public void push(int val) {
        if (minimums.isEmpty() || val <= minimums.peekFirst()) {
            minimums.push(val);
        }
        data.push(val);
    }

    public void pop() {
        int val = data.pop();
        if (minimums.peekFirst() == val) {
            minimums.pop();
        }
    }

    public int top() {
        return data.peek();
    }

    public int getMin() {
        return minimums.peekFirst();
    }
}
