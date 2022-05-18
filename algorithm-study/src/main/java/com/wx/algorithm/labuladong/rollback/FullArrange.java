package com.wx.algorithm.labuladong.rollback;

import java.util.LinkedList;
import java.util.List;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/18
 */
public class FullArrange {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        List<List<Integer>> permute = permute(nums);
        for (List<Integer> list : permute) {
            for (Integer integer : list) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> tracked = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        backTrack(nums, tracked, used);
        return RES_LIST;
    }

    private static final List<List<Integer>> RES_LIST = new LinkedList<>();

    private static void backTrack(int[] nums, LinkedList<Integer> list, boolean[] used) {
        if (nums.length == list.size()) {
            RES_LIST.add(new LinkedList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }

            list.add(nums[i]);
            used[i] = true;
            backTrack(nums, list, used);
            list.removeLast();
            used[i] = false;
        }
    }
}
