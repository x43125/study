package com.wx.algorithm.leetcode.normal;

/**
 * @author wangxiang
 * @date 2022/8/19 10:14
 * @description
 */
public class T_1560_StudyStudentsNo {
    public static void main(String[] args) {

    }

    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int count = 0;
        for (int i = 0; i < startTime.length; i++) {
            if (startTime[i] <= queryTime && endTime[i] >= queryTime) {
                count++;
            }
        }

        return count;
    }
}
