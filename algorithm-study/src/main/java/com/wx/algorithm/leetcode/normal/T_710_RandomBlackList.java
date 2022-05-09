package com.wx.algorithm.leetcode.normal;

import java.util.*;

/**
 * @Descrption: todo
 * @Author: x43125
 * @Date: 22/05/08
 */
public class T_710_RandomBlackList {
    private final Map<Integer, Integer> map;
    private final Random random = new Random();
    private final int length;

    public T_710_RandomBlackList(int n, int[] blacklist) {
        map = new LinkedHashMap<>();
        length = n - blacklist.length;
        Arrays.sort(blacklist);
        for (int j : blacklist) {
            map.put(j, j);
        }

        int i = n - 1;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            while (map.containsKey(i) && i >= 0) {
                i--;
            }
            if (i < 0) {
                break;
            }
            entry.setValue(i);
            i--;
        }
    }

    public int pick() {
        int i1 = random.nextInt(length);
        return map.getOrDefault(i1, i1);
    }

    public static void main(String[] args) {
        int n = 4;
        int[] blackList = {2, 1};
        T_710_RandomBlackList randomBlackList = new T_710_RandomBlackList(n, blackList);
//        T_710_RandomBlackList randomBlackList = new T_710_RandomBlackList(0, new int[]{});
        for (int i = 0; i < 20; i++) {
            int pick = randomBlackList.pick();
            System.out.print(pick + " ");
        }

        /*
        ["Solution","pick","pick","pick","pick","pick","pick","pick","pick","pick","pick","pick","pick","pick","pick","pick","pick","pick","pick","pick","pick","pick","pick","pick","pick","pick","pick","pick","pick","pick","pick"]
[[3,[0]],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[]]
         */
    }
}
