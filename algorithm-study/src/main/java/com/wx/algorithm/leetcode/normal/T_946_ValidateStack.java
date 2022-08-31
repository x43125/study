/*
 * @Author: wangxiang wangxiang@flashhold.com
 * @Date: 2022-08-31 10:25:44
 * @LastEditors: wangxiang wangxiang@flashhold.com
 * @LastEditTime: 2022-08-31 11:32:55
 * @FilePath: \algorithm-study\src\main\java\com\wx\algorithm\leetcode\normal\T_946_VerifyStack.java
 * @Description: 给定 pushed 和 popped 两个序列，每个序列中的 值都不重复，
 * 只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时，返回 true；否则，返回 false 。
 * 1. 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * 输出：true
 * 2. 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * 输出：false
 */
package com.wx.algorithm.leetcode.normal;

import java.util.ArrayDeque;
import java.util.Deque;

public class T_946_ValidateStack {

    public static void main(String[] args) {
        int[] pushed = { 1, 2, 3, 4, 5 };
        int[] popped = { 4, 5, 3, 2, 1 };

        T_946_ValidateStack validateStack = new T_946_ValidateStack();
        boolean res = validateStack.validateStackSequences(pushed, popped);
        System.out.println(res);
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = 0, j = 0; i < pushed.length; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }

}
