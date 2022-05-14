package com.wx.algorithm.leetcode.contest.weekend.w0514;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/14
 */
public class CoverMax {
    public static int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        Arrays.sort(tiles, Comparator.comparingInt(a -> a[0]));

//        for (int[] tile : tiles) {
//            for (int i : tile) {
//                System.out.print(i + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("=========================");

        List<List<Integer>> newTiles = new ArrayList<>();
        List<Integer> list = new ArrayList<>(2);
        list.add(tiles[0][0]);
        list.add(tiles[0][1]);
        newTiles.add(list);

        for (int i = 1; i < tiles.length; i++) {
            int n = newTiles.size() - 1;
            Integer right = newTiles.get(n).get(1);
            if (right >= tiles[i][1]) {
            } else if (right >= tiles[i][0]) {
                List<Integer> integers = newTiles.get(n);
                integers.set(1, tiles[i][1]);
                newTiles.set(n, integers);
            } else {
                list = new ArrayList<>(2);
                list.add(tiles[i][0]);
                list.add(tiles[i][1]);
                newTiles.add(list);
            }
        }

        for (List<Integer> newTile : newTiles) {
            for (Integer integer : newTile) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
        System.out.println("===============");

        int nag = 0;
        int sum = 0;
        int res = 0;
        for (int i = 0; i < newTiles.size() - 1; i++) {
            int left = newTiles.get(i).get(0);
            int right = newTiles.get(i).get(1);
            if (right - left >= carpetLen) {
                return carpetLen;
            }
            if (newTiles.get(i + 1).get(0) - left >= carpetLen) {
                res = Math.max(res, right - left);
            } else {
                nag = newTiles.get(i + 1).get(0) - right;
                sum += newTiles.get(i + 1).get(0) - left;
            }



        }


//        int[] arr = new int[newTiles.size() * 2 - 1];
//        for (int i = 0; i < arr.length; i++) {
//            if (i % 2 == 0) {
//                arr[i] = newTiles.get(i / 2).get(1) - newTiles.get(i / 2).get(0);
//            } else {
//                arr[i] = newTiles.get(i / 2 + 1).get(0) - newTiles.get(i / 2).get(1);
//            }
//        }
//
//        for (int i : arr) {
//            System.out.print(i + " ");
//        }
//        System.out.println("\n===============");
//
//
//        int nag = 0;
//        int res = 0;
//        int sum = 0;
//        for (int i = 0; i < arr.length; i++) {
//            int gap= arr[i];
//            if (i % 2 == 1) {
//                nag += gap;
//            }
//            sum += gap;
//            if (sum >= carpetLen) {
//                int temp = carpetLen - nag;
//                res = Math.max(res, temp);
//                nag = 0;
//                sum = gap;
//            }
//
//        }


        return res;
    }

    public static void main(String[] args) {
        int[][] tiles = {{1, 5}, {10, 13}, {12, 26}, {20, 25}, {30, 32}};
        int carpetLen = 10;
        int i = maximumWhiteTiles(tiles, carpetLen);
        System.out.println(i);
    }
}
