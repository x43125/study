package com.wx.algorithm.labuladong.array.prefixsum;

import java.util.HashMap;
import java.util.Map;

public class OneDimensionalAreaSum {

    private int[] prefixArr;

    /**
     * usual function
     * 
     * @param arr
     * @param start
     * @param end
     * @return
     */
    public static int getAreaSum(int[] arr, int start, int end) {
        if (end >= arr.length) {
            throw new RuntimeException("array index out of bound");
        }
        int result = 0;
        for (int i = start; i <= end; i++) {
            result += arr[i];
        }
        return result;
    }

    public int getAreaSumWithPrefixSum(int[] arr, int start, int end) {
        if (end >= prefixArr.length) {
            throw new RuntimeException("array index out of bound");
        }
        int result = prefixArr[end + 1] - prefixArr[start];
        return result;
    }

    private void buildPrefixSumArr(int[] arr) {
        int size = arr.length;
        this.prefixArr = new int[size + 1];
        prefixArr[0] = 0;

        for (int i = 1; i < size; i++) {
            prefixArr[i + 1] = arr[i] + prefixArr[i];
        }
    }

    public int subArraySum(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < prefixArr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (prefixArr[i] - prefixArr[j] == k) {
                    res++;
                }
            }

        }
        return res;
    }

    public int subArraySumWithHash(int[] nums, int k) {
        int n = nums.length;
        // map：前缀和 -> 该前缀和出现的次数
        Map<Integer, Integer> preSum = new HashMap<>();
        //todo 未理解 base case 
        preSum.put(0, 1);

        int res = 0;
        int sum0_i = 0;
        for (int i = 0; i < n; i++) {
            sum0_i += nums[i];
            // 这是我们想找的前缀和 nums[0..j]
            int sum0_j = sum0_i - k;
            // 如果前面有这个前缀和，则直接更新答案
            if (preSum.containsKey(sum0_j)) {
                res += preSum.get(sum0_j);
            }
            // 把前缀和 nums[0..i] 加入并记录出现次数
            preSum.put(sum0_i, preSum.getOrDefault(sum0_i, 0) + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 1,1 };
        int left = 1;
        int right = 2;

        int sum = getAreaSum(nums, left, right);
        System.out.println(sum);
        System.out.println("================================");

        OneDimensionalAreaSum areaSum = new OneDimensionalAreaSum();
        areaSum.buildPrefixSumArr(nums);
        System.out.println(areaSum.getAreaSumWithPrefixSum(nums, left, right));

        System.out.println("================================");
        System.out.println(areaSum.subArraySum(nums, 0));
        System.out.println("================================");
        System.out.println(areaSum.subArraySumWithHash(nums, 2));
    }
}
