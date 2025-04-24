package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_215_KLargest {

    int[] heap;
    int n;

    /**
     * 这题能想到小顶堆，太妙了
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        n = 0;
        heap = new int[k];
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (n < k) {
                heap[n] = num;
                swim(n++);
            } else if (num > heap[0]) {
                // 这个最多k,然后转换的思路太妙了
                heap[0] = num;
                sink(0);
            }
        }

        return heap[0];
    }

    private void swim(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (heap[parent] < heap[i]) {
                break;
            }
            swap(heap, i, parent);
            i = parent;
        }
    }

    private void sink(int i) {
        while (i * 2 + 1 < n) {
            int left = i * 2 + 1, right = i * 2 + 2, min = left;
            if (right < n && heap[right] < heap[left]) {
                min = right;
            }
            if (heap[i] <= heap[min]) {
                break;
            }
            swap(heap, i, min);
            i = min;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = { 3, 2, 1, 5, 6, 4 };
        int k = 6;
        T_215_KLargest kLargest = new T_215_KLargest();
        int kthLargest = kLargest.findKthLargest(nums, k);
        System.out.println(kthLargest);
    }
}
