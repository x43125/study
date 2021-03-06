package com.wx.algorithm.leetcode.contest.spring;

import java.util.Arrays;

/**
 * @Descrption: 勇者背包内共有编号为 0 ~ 4 的五种食材，其中 meterials[j] 表示第 j 种食材的数量。
 * 通过这些食材可以制作若干料理，cookbooks[i][j] 表示制作第 i 种料理需要第 j 种食材的数量，
 * 而 attribute[i] = [x,y] 表示第 i 道料理的美味度 x 和饱腹感 y。
 * <p>
 * 在饱腹感不小于 limit 的情况下，请返回勇者可获得的最大美味度。如果无法满足饱腹感要求，则返回 -1。
 * <p>
 * 注意：
 * 每种料理只能制作一次。
 * @Author: x43125
 * @Date: 22/04/16
 */
public class Cook {
    /*
     * 输入：
     * meterials = [3,2,4,1,2] // j 种食材的数量。
     * cookbooks = [[1,1,0,1,2],[2,1,4,0,0],[3,2,4,1,0]] //制作第 i 种料理需要第 j 种食材的数量，
     * attribute = [[3,2],[2,4],[7,6]] // 第 i 道料理的美味度 x 和饱腹感 y。
     * limit = 5 // 饱腹感不小于 limit
     *
     * 输出：7
     *
     * 解释：
     * 食材数量可以满足以下两种方案：
     * 方案一：制作料理 0 和料理 1，可获得饱腹感 2+4、美味度 3+2
     * 方案二：仅制作料理 2， 可饱腹感为 6、美味度为 7
     * 因此在满足饱腹感的要求下，可获得最高美味度 7
     */
    // 穷举
    public static int perfectMenu(int[] materials, int[][] cookbooks, int[][] attribute, int limit) {
        int maxD = -1;
        int materialsLength = materials.length;
        int cookbookLength = cookbooks[0].length;
        int cookbooksLength = cookbooks.length;

        // 穷举所有可能性
        for (int m = 0; m < cookbooksLength; m++) {
            boolean breakFlag = false;
            for (int n = m; n < cookbooksLength; n++) {
                int[] tempMaterials = Arrays.copyOf(materials, materialsLength);

                // 从m到n遍历所有可能性
                int i;
                for (i = m; i <= n; i++) {
                    int[] cookbook = cookbooks[i];
                    boolean canUse = true;

                    // 符合条件的 -> 原料足够的才算
                    for (int j = 0; j < cookbookLength; j++) {
                        if (tempMaterials[j] < cookbook[j]) {
                            canUse = false;
                            break;
                        }
                        tempMaterials[j] -= cookbook[j];
                    }
                    if (!canUse) {
                        breakFlag = true;
                        break;
                    }
                }
                if (breakFlag) {
                    continue;
                }
                int sumB = 0;
                int sumD = 0;
                for (i = m; i <= n; i++) {
                    sumB += attribute[i][1];
                    sumD += attribute[i][0];
                }
                if (sumB >= limit) {
                    maxD = Math.max(maxD, sumD);
                }
            }
        }
        return maxD;
    }

    public static int perfectMenu_02(int[] materials, int[][] cookbooks, int[][] attribute, int limit) {
        int maxD = -1;
        int materialsLength = materials.length;
        int cookbookLength = cookbooks[0].length;
        int cookbooksLength = cookbooks.length;

        for (int i = 0; i < cookbooksLength; i++) {

        }

        return maxD;
    }

    int max = 0;
    int sum = 0;

    /**
     * 使用深度优先搜索
     * @param materials
     * @param cookbooks
     * @param attribute
     * @param limit
     * @return
     */
    public int perfectMenu_3(int[] materials, int[][] cookbooks, int[][] attribute, int limit) {
        dfs(materials, cookbooks, attribute, limit, 0, 0);
        if (max == 0) {
            return -1;
        }
        return max;
    }

    public void dfs(int[] materials, int[][] cookbooks, int[][] attribute, int limit, int start, int limitSum) {
        if (start == cookbooks.length) {
            return;
        }

        for (int i = start; i < cookbooks.length; i++) {
            boolean flag = true;
            int j = 0;
            for (; j <= 4; j++) {
                materials[j] = materials[j] - cookbooks[i][j];
                if (materials[j] < 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                limitSum += attribute[i][1];
                sum += attribute[i][0];
                if (limitSum >= limit) {
                    max = Math.max(sum, max);
                }
            } else {
                // 将materials[j] = materials[j] - cookbooks[i][j]; 提前减掉的补回来
                for (int k = 0; k <= j; k++) {
                    materials[k] = materials[k] + cookbooks[i][k];
                }
            }
            // dfs
            dfs(materials, cookbooks, attribute, limit, i + 1, limitSum);
            // 回溯
            if (flag) {
                for (int k = 0; k <= 4; k++) {
                    materials[k] += cookbooks[i][k];
                }
                limitSum -= attribute[i][1];
                sum -= attribute[i][0];
            }
        }
    }

    public static void main(String[] args) {
        // int[] meterials = { 3, 2, 4, 1, 2 };
        // int[][] cookbooks = { { 1, 1, 0, 1, 2 }, { 2, 1, 4, 0, 0 }, { 3, 2, 4, 1, 0 } };
        // int[][] attribute = { { 3, 2 }, { 2, 4 }, { 7, 6 } };
        // int limit = 5;

        // int[] meterials ={10,10,10,10,10};
        // int[][] cookbooks = {{10,1,1,1,1},{3,3,3,3,3},{4,4,4,4,4}};
        // int[][] attribute = {{17,5},{6,6},{10,10}};
        // int limit = 5;

//        int[] meterials ={0,0,0,0,0};
//        int[][] cookbooks = {{1,0,0,0,0},{1,0,0,0,0},{0,0,0,0,0}};
//        int[][] attribute = {{5,5},{6,6},{10,10}};
//        int limit = 5;

        int[] meterials = {13, 17, 19, 17, 5};
        int[][] cookbooks = {{17, 0, 8, 0, 9}, {7, 11, 0, 1, 4}, {1, 20, 2, 11, 17}, {11, 14, 6, 12, 18},
                            {4, 18, 1, 11, 2}, {17, 7, 0, 6, 17}, {1, 6, 8, 2, 1}, {14, 14, 12, 16, 10}};
        int[][] attribute = {{19, 1}, {13, 11}, {3, 15}, {6, 2}, {19, 19}, {13, 17}, {20, 17}, {17, 19}};
        int limit = 8;

        int perfectMenu = perfectMenu(meterials, cookbooks, attribute, limit);
        System.out.println(perfectMenu);
    }

}
