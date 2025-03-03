package com.wx.algorithm.algorithm4.sort;

import java.util.Scanner;

public class SortTest {
    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
        // String[] a = sc.nextLine().split(" ");
        // sc.close();
        // Sort sort = new ChooseSort();
        // Sort sort = new InsertSort();
        // Sort sort = new ShellSort();
        // Sort sort = new MergeSort();
        // Sort sort = new MergeSortBU();
        // Sort sort = new QuickSort();
        // sort.sort(a);
        // assert sort.isSorted(a);
        // sort.show(a);

        MaxPQ<Integer> maxPQ = new MaxPQ<>(5);
        maxPQ.insert(5);
        maxPQ.insert(10);
        maxPQ.insert(22);
        maxPQ.insert(2);
        maxPQ.insert(3);
        while (!maxPQ.isEmpty()) {
            System.out.println(maxPQ.delMax());
        }
    }
}
