package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.Stack;

public class T_155_MinStack {

    Stack<Integer> stack;
    Stack<Integer> minStack;

    public T_155_MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        stack.add(val);
        minStack.push(Math.min(minStack.peek(), val));
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        T_155_MinStack minStack = new T_155_MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }
}
