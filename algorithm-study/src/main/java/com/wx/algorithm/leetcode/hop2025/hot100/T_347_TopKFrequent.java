package com.wx.algorithm.leetcode.hop2025.hot100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class T_347_TopKFrequent {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        List<Frequent> list = new ArrayList<>();
        for (Entry<Integer, Integer> entry : countMap.entrySet()) {
            Frequent frequent = new Frequent(entry.getKey(), entry.getValue());
            list.add(frequent);
        }

        Frequent[] heap = new Frequent[k];
        for (int i = 0; i < list.size(); i++) {
            if (i < k) {
                heap[i] = list.get(i);
                swim(heap, i);
            } else {
                if (list.get(i).count > heap[0].count) {
                    heap[0] = list.get(i);
                    sink(heap, 0);
                }
            }
        }

        int[] topK = new int[k];
        for (int i = 0; i < heap.length; i++) {
            topK[i] = heap[i].val;
        }
        return topK;
    }

    private void sink(Frequent[] heap, int i) {
        int n = heap.length;
        while (i * 2 + 1 < n) {
            int left = i * 2 + 1, right = left + 1;
            if (right < n && heap[left].count > heap[right].count) {
                left = right;
            }
            if (heap[i].count > heap[left].count) {
                swap(heap, i, left);
                i = left;
            } else {
                break;
            }
        }
    }

    private void swap(Frequent[] heap, int i, int j) {
        Frequent temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private void swim(Frequent[] heap, int i) {
        while (i > 0) {
            int parent = (i + 1) / 2 - 1;
            if (heap[i].count < heap[parent].count) {
                swap(heap, i, parent);
                i = parent;
            } else {
                break;
            }
        }
    }

    static class Frequent {
        int val;
        int count;

        public Frequent(int val, int count) {
            this.val = val;
            this.count = count;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3,3,3,3};
        T_347_TopKFrequent t_347_TopKFrequent = new T_347_TopKFrequent();
        int[] topKFrequent = t_347_TopKFrequent.topKFrequent(nums, 2);
        for (int top : topKFrequent) {
            System.out.println(top);
        }
    }
}
