package alg.search;

import alg.sort.Sort;

public class Search {
    // 顺序查找
    public int sequenceSeach(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }

        return -1;
    }

    // 二分法:只对已排序生效
    public int binarySeach(int[] nums, int target) {
        Sort sort = new Sort();
        sort.quickSort(nums);
        return binarySeach(nums, 0, nums.length - 1, target);
    }

    private int binarySeach(int[] nums, int l, int r, int target) {
        if (l > r) {
            return -1;
        }

        int mid = (r - l) / 2 + l;
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] < target) {
            return binarySeach(nums, mid + 1, r, target);
        } else {
            return binarySeach(nums, l, mid - 1, target);
        }
    }

    public static void main(String[] args) {
        int[] nums = { 3, 5, 7, 2, 4 };
        int target = 7;
        Search search = new Search();
        // int targetIndex = search.sequenceSeach(nums, target);
        int targetIndex = search.binarySeach(nums, target);
        System.out.println(targetIndex);
    }
}
