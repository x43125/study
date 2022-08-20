package com.wx.algorithm.leetcode.normal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author wangxiang
 * @date 2022/8/16 19:54
 * @description
 */
public class T_1775_OrderedStream {
    private Integer ptr;
    private Map<Integer, String> map;

    public T_1775_OrderedStream(int n) {
        map = new TreeMap<>(Integer::compareTo);
        ptr = 1;
    }

    public List<String> insert(int idKey, String value) {
        map.put(idKey, value);
        List<String> res = new ArrayList<>();
        List<Integer> resIndex = new ArrayList<>();
        if (map.containsKey(ptr)) {
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                if (entry.getKey() < ptr) {
                    continue;
                }

                if (entry.getKey().equals(ptr)) {
                    res.add(entry.getValue());
                    resIndex.add(entry.getKey());
                    ptr++;
                } else {
                    break;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {

    }
}
