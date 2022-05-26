package com.wx.algorithm.leetcode.normal;

import java.util.*;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/26
 */
public class T_752_OpenTheLock {

    public static void main(String[] args) {
        String[] deadends = {"0000"};
        String target = "8888";

        T_752_OpenTheLock openTheLock = new T_752_OpenTheLock();
        int i = openTheLock.openLock(deadends, target);

        System.out.println(i);
    }


    public int openLock(String[] deadends, String target) {
        int res = 0;
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>(Arrays.asList(deadends));
        if (visited.contains("0000")) {
            return -1;
        }

        queue.offer("0000");
        visited.add("0000");

        while (!queue.isEmpty()) {
            int length = queue.size();

            for (int i = 0; i < length; i++) {
                String cur = queue.poll();

                if (target.equals(cur)) {
                    return res;
                }

                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up)) {
                        queue.offer(up);
                        visited.add(up);
                    }
                    String down = minusOne(cur, j);
                    if (!visited.contains(down)) {
                        queue.offer(down);
                        visited.add(down);
                    }
                }
            }
            res++;
        }
        return -1;
    }

    private String minusOne(String cur, int j) {
        char[] chars = cur.toCharArray();
        if (chars[j] == '0') {
            chars[j] = '9';
        } else {
            chars[j] -= 1;
        }

        return new String(chars);
    }

    private String plusOne(String cur, int j) {
        char[] chars = cur.toCharArray();
        if (chars[j] == '9') {
            chars[j] = '0';
        } else {
            chars[j] += 1;
        }

        return new String(chars);
    }
}
