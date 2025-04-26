package com.wx.algorithm.leetcode.hop2025.hot100;

public class T_215_KLargest {

    /**
     * 这题能想到小顶堆，太妙了
     * 
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        // 设置一个只有k容量的小顶堆
        // 当容量小于k的时候先向里面添加k个数字，每次添加后，将其上浮到指定位置
        // 大于等于k之后添加的数字，与顶比较，如果大于则替换顶端值，然后将其下沉到指定位置
        // 最后留下来的k个数，即是最大的k个数
        // 顶上即为第k大的数
        int[] heap = new int[k];
        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                heap[i] = nums[i];
                swim(heap, i);
            } else {
                if (nums[i] > heap[0]) {
                    heap[0] = nums[i];
                    sink(heap, 0);
                }
            }
        }

        return heap[0];
    }

    /**
     * 下沉
     * 
     * @param heap
     * @param i
     */
    private void sink(int[] heap, int i) {
        int n = heap.length;
        while (i * 2 + 1 < n) {
            int left = i * 2 + 1, right = left + 1;
            if (right < n) {
                left = heap[left] < heap[right] ? left : right;
            }
            if (heap[i] > heap[left]) {
                swap(heap, i, left);
                i = left;
            } else {
                break;
            }
        }
    }

    /**
     * 上浮
     * 
     * @param heap
     * @param i
     */
    private void swim(int[] heap, int i) {
        // 上浮，只需要和父节点相比即可
        while (i > 0) {
            int parent = (i + 1) / 2 - 1;
            if (heap[i] < heap[parent]) {
                swap(heap, i, parent);
                i = parent;
            } else {
                break;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = { 3, 2, 3, 1, 2, 4, 5, 5, 6 };
        int k = 9;
        T_215_KLargest kLargest = new T_215_KLargest();
        int kthLargest = kLargest.findKthLargest(nums, k);
        System.out.println(kthLargest);
    }
}
