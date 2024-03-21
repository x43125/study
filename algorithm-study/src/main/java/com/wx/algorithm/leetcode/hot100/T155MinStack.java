package com.wx.algorithm.leetcode.hot100;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author Shawn
 * @date 2024/3/21 22:10
 * @description
 */
public class T155MinStack {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);

        System.out.println("minStack.getMin() = " + minStack.getMin());
        minStack.pop();
        System.out.println("minStack.top() = " + minStack.top());
        System.out.println("minStack.getMin() = " + minStack.getMin());
    }

    static class MinStack {
        private Stack<Integer> stack;
        private Stack<Integer> minStack;

        public MinStack() {
            this.stack = new Stack<>();
            this.minStack = new Stack<>();
        }

        public void push(int val) {
            minStack.push(Math.min(minStack.size() == 0 ? Integer.MAX_VALUE : minStack.peek(), val));
            stack.push(val);
        }

        public void pop() {
            minStack.pop();
            stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }
}
