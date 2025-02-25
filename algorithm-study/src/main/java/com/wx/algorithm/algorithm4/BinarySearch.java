package com.wx.algorithm.algorithm4;

public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = { 1, 3, 6, 7, 9, 11, 222 };
        int target = 66;
        int targetIndex = binarySearch_01(arr, target);
        if (targetIndex == -1) {
            System.out.println("not found, target:" + target + ", index:" + targetIndex);
        } else {
            System.out.println("found, target:" + target + ", index:" + targetIndex + ", result:"
                    + arr[targetIndex]);
        }

    }

    // 非递归
    public static int binarySearch_02(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }

    // 递归方式
    public static int binarySearch_01(int[] arr, int target) {
        return binarySearch(arr, 0, arr.length - 1, target);
    }

    public static int binarySearch(int[] arr, int left, int right, int target) {
        // 必须有=，否则无法确定是否为最后（递归到最后）一个值
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                return binarySearch(arr, left, mid - 1, target);
            } else if (arr[mid] < target) {
                return binarySearch(arr, mid + 1, right, target);
            }
        }

        return -1;
    }
}
