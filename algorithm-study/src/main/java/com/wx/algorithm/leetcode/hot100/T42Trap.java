package com.wx.algorithm.leetcode.hot100;

/**
 * 经典之 接雨水
 * @author wangxiang
 * @date 2023/6/17 23:03
 * @description
 */
public class T42Trap {

    public static void main(String[] args) {
//        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
//        int[] height = {9, 6, 8, 8, 5, 6, 3};
        int[] height = {8, 6, 8, 9, 10, 5, 6, 3};
//        int[] height = {4,2,0,3,2,5};
//        int[] height = {5, 4, 1, 2};
        int trap = trap(height);
        System.out.println("trap = " + trap);
    }

    /**
     * 1. 需要出现 先降再升 的情况才能储存水
     * 2. 当先降再升之后，直到找到将降的地方
     * 3. 选择较小的那个作为高，两只之间的距离作为底 底乘高 == 总面积
     * 4. 总面积 - 两边之间的柱子的高度之和 == 结果
     *
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        int result = 0;
        int n = height.length;
        int i = 0, j = 1;
        while (i < j && j < n) {
            if (height[i] <= height[j]) {
                i++;
                j++;
            } else {
                // 向右找，直到找到：
                // 1. 到头
                // 2. 比左侧边高或相等
                int index = j;
                while (j < n - 1) {
                    j++;
                    if (height[j] > height[index]) {
                        index = j;
                        if (height[j] >= height[i]) {
                            break;
                        }
                    }
                }
                // 1. 到头
                // 左侧也要向右找到最接近的，否则会多减
                while (i < index) {
                    i++;
                    if (i >= index || height[i] < height[index]) {
                        i--;
                        break;
                    }
                }

                int left = i, right = index, sum = 0;
                System.out.print(left + " " + right + " " + height[left] + " " + height[right] + " ");
                while (left + 1 < right) {
                    sum += height[left + 1];
                    left++;
                }


                result += (Math.min(height[i], height[index]) * (index - i - 1)) - sum;
                System.out.println(sum);
                i = index;
                j = index + 1;
            }
        }

        return result;
    }
}
