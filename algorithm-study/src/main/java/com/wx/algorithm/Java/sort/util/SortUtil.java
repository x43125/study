package com.wx.algorithm.Java.sort.util;

public class SortUtil {
    public static int[] init(long num) {
        String numStr = num + "";
        int[] arr = new int[numStr.length()];
        for (int i = 0; i < numStr.length(); ++i) {
            arr[i] = Integer.parseInt(numStr.charAt(i) + "");
        }
        return arr;
    }

    public static void exch(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    public static void print(int[] arr) {
        for (int i : arr) {
            System.out.printf(i + " ");
        }
        System.out.println();
    }

}
