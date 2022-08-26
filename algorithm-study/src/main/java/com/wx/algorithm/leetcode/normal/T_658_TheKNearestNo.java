package com.wx.algorithm.leetcode.normal;

import java.util.*;

/**
 * @author wangxiang
 * @date 2022/8/25 10:46
 * @description 输入：arr = [1,2,3,4,5], k = 4, x = 3
 * 输出：[1,2,3,4]
 * <p>
 * [1,1,1,10,10,10]
 * 1
 * 9
 */
public class T_658_TheKNearestNo {
    public static void main(String[] args) {
//        int[] arr = {1,2,3,4,5,6,7,8};
        int[] arr = {1, 1, 1, 11, 11, 11};
        int k = 1, x = 7;
        T_658_TheKNearestNo theKNearestNo = new T_658_TheKNearestNo();
        List<Integer> list = theKNearestNo.findClosestElements_2(arr, k, x);
        list.forEach(System.out::println);
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        Queue<Integer> queue = new LinkedList<>();
        for (int no : arr) {
            if (queue.size() < k) {
                queue.offer(no);
            } else {
                if (no == queue.peek()) {
                } else if (Math.abs(no - x) >= Math.abs(queue.peek() - x)) {
                    break;
                } else {
                    queue.poll();
                    queue.offer(no);
                }
            }
        }

        return new ArrayList<>(queue);
    }

    public List<Integer> findClosestElements_2(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - 1;

        while (right - left != k - 1) {
            if (x - arr[left] > arr[right] - x) {
                left++;
            } else {
                right--;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            list.add(arr[i]);
        }
        return list;
    }
}
