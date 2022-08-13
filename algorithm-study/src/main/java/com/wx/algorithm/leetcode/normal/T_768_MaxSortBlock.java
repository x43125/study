package com.wx.algorithm.leetcode.normal;

import java.util.*;

/**
 * @Descrption: todo 未看懂
 * @Author: x43125
 * @Date: 22/08/13
 */
public class T_768_MaxSortBlock {
    public static void main(String[] args) {
        int[] arr = {2,1,3,4,4};
        T_768_MaxSortBlock maxSortBlock = new T_768_MaxSortBlock();
        int i = maxSortBlock.maxChunksToSorted(arr);
        System.out.println(i);
    }

    /*
    输入: arr = [2,1,3,4,4]
    输出: 4
    解释:
    我们可以把它分成两块，例如 [2, 1], [3, 4, 4]。
    然而，分成 [2, 1], [3], [4], [4] 可以得到最多的块数。

    来源：力扣（LeetCode）
    链接：https://leetcode.cn/problems/max-chunks-to-make-sorted-ii
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public int maxChunksToSorted(int[] arr) {
        Map<Integer, Integer> count = new HashMap<Integer, Integer>();
        int res = 0;
        int[] sortedArr = new int[arr.length];
        System.arraycopy(arr, 0, sortedArr, 0, arr.length);
        Arrays.sort(sortedArr);

        for (int i = 0; i < sortedArr.length; i++) {
            int x = arr[i], y = sortedArr[i];
            count.put(x, count.getOrDefault(x, 0) + 1);
            if (count.get(x) == 0) {
                count.remove(x);
            }
            count.put(y, count.getOrDefault(y, 0) - 1);
            if (count.get(y) == 0) {
                count.remove(y);
            }
            if (count.isEmpty()) {
                res++;
            }
        }
        return res;
    }
}
