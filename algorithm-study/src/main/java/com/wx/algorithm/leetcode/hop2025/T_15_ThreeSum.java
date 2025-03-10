package com.wx.algorithm.leetcode.hop2025;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class T_15_ThreeSum {
    public static void main(String[] args) {
        int[] num = new int[]{-1,0,1,2,-1,-4};
        T_15_ThreeSum t_15_ThreeSum = new T_15_ThreeSum();
        List<List<Integer>> threeSum = t_15_ThreeSum.threeSum(num);
        for (List<Integer> list : threeSum) {
            for (Integer value : list) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    // 暴力法
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        for (int i=0; i<n-2; i++) {
            for (int j=i+1; j<n-1; j++) {
                for (int k=j+1; k<n; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> oneAns = new ArrayList<>();
                        oneAns.add(nums[i]);
                        oneAns.add(nums[j]);
                        oneAns.add(nums[k]);
                        if (!contains(result, oneAns)) {
                            result.add(oneAns);
                        }
                    }
                }
            }
        }

        return result;
    }

    private boolean contains(List<List<Integer>> parent, List<Integer> child) {
        for (List<Integer> list : parent) {
            if (equals(list, child)) {
                return true;
            }
        }

        return false;
    }

    private boolean equals(List<Integer> a, List<Integer> b) {
        if (a.size() != b.size()) {
            return false;
        }
        Map<Integer, Integer> mapA = new HashMap<>();
        Map<Integer, Integer> mapB = new HashMap<>();
        for(int i=0; i<a.size(); i++) {
            mapA.put(a.get(i), mapA.getOrDefault(a.get(i), 0)+1);
            mapB.put(b.get(i), mapB.getOrDefault(b.get(i), 0)+1);
        }

        for (Map.Entry<Integer, Integer> entry : mapA.entrySet()) {
            if (mapB.get(entry.getKey()) != entry.getValue()) {
                return false;
            }
        }

        return true;
    }
}
