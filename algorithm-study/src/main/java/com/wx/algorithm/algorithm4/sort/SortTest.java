package com.wx.algorithm.algorithm4.sort;

import java.util.Scanner;

public class SortTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] a = sc.nextLine().split(" ");
        sc.close();
        // Sort sort = new ChooseSort();
        // Sort sort = new InsertSort();
        Sort sort = new ShellSort();
        sort.sort(a);
        assert sort.isSorted(a);
        sort.show(a);
    }
}
