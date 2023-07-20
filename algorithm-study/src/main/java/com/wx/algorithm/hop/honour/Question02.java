package com.wx.algorithm.hop.honour;

import java.util.*;

/**
 * @author Shawn
 * @date 2023/7/20 20:10
 * @description
 */
public class Question02 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = 0;
        int nowCount = 0;
        List<Integer> list = new ArrayList<>();
        while (in.hasNextInt()) {
            if (count == 0) {
                count = in.nextInt();
            } else {
                list.add(in.nextInt());
                nowCount++;
            }
            if (nowCount == count) {
                break;
            }
        }
        List<String> result = new ArrayList<>();
        list.forEach(num -> result.add(gameCanSuccess(num)));
        result.forEach(System.out::println);
    }

    /**
     * 判断此数是否为部分相连的素数之和
     *
     * @param num
     * @return
     */
    private static String gameCanSuccess(int num) {
        // 素数列表
        // 素数的前缀和
        Set<Integer> preSumSet = new HashSet<>();
        preSumSet.add(0);
        int preSum = 0;
        for (int i = 2; i <= num; i++) {
            if (isSu(i)) {
                preSum += i;
                if (preSumSet.contains(preSum - num)) {
                    return "yes";
                } else {
                    preSumSet.add(preSum);
                }
            }
        }

        return "no";
    }

    private static boolean isSu(int number) {
        int i;
        for (i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return i > Math.sqrt(number);
    }
}
