package com.wx.algorithm.leetcode.contest.weekend;

import java.util.HashSet;
import java.util.Set;

public class T_6042_CountPoints {

    public static int countLatticePoints(int[][] circles) {

        Set<String> set = new HashSet<>();
        for (int[] circle : circles) {
            int xCenter = circle[0];
            int yCenter = circle[1];
            int radius = circle[2];

            int xStart = xCenter - radius;
            int yStart = yCenter - radius;

            int squareRadius = circle[2] * circle[2];

            for (int i = xStart; i <= xCenter; i++) {
                int squareRight = (xCenter - i) * (xCenter - i);
                int expectSquareLeft = squareRadius - squareRight;
                // 可以优化到中间，剩下一面对称的
                for (int j = yStart; j <= yCenter; j++) {
                    int squareLeft = (yCenter - j) * (yCenter - j);

                    if (squareLeft <= expectSquareLeft) {
                        set.add(i + "-" + j);
                        // x对称；y对称；圆心对称
                        set.add((xCenter * 2 - i) + "-" + j);
                        set.add(i + "-" + (yCenter * 2 - j));
                        set.add((xCenter * 2 - i) + "-" + (yCenter * 2 - j));
                    }
                }
            }
        }
        return set.size();
    }

    public static void main(String[] args) {
        int[][] circles = { { 2, 2, 2 }, { 3, 4, 1 } };
        int countLatticePoints = countLatticePoints(circles);
        System.out.println(countLatticePoints);
    }
}