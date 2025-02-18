package com.wx.algorithm.leetcode.hot100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangxiang
 * @date 2023/6/13 22:53
 * @description
 */
public class T49StringMatch {
    public static void main(String[] args) {
        String[] strs = {"cab","tin","pew","duh","may","ill","buy","bar","max","doc"};
        List<List<String>> lists = groupAnagrams_2(strs);
        for (List<String> list : lists) {
            for (String s : list) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        int n = strs.length;
        Map<Integer, List<List<String>>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String str = strs[i];
            int sum = 0;
            for (char c : str.toCharArray()) {
                sum += c;
            }

            if (map.containsKey(sum)) {
                boolean findSame = false;
                List<List<String>> linkedList = map.get(sum);
                for (List<String> nowList : linkedList) {
                    String modelStr = nowList.get(0);
                    Map<Character, Integer> countMap = new HashMap<>();
                    for (char c : modelStr.toCharArray()) {
                        if (countMap.containsKey(c)) {
                            countMap.put(c, countMap.get(c) + 1);
                        } else {
                            countMap.put(c, 1);
                        }
                    }

                    Map<Character, Integer> strMap = new HashMap<>();
                    for (char c : str.toCharArray()) {
                        if (strMap.containsKey(c)) {
                            strMap.put(c, strMap.get(c) + 1);
                        } else {
                            strMap.put(c, 1);
                        }
                    }

                    boolean flag = false;
                    for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
                        if (!strMap.containsKey(entry.getKey()) || !strMap.get(entry.getKey()).equals(entry.getValue())) {
                            flag = true;
                            break;
                        }
                    }

                    if (!flag) {
                        nowList.add(str);
                        findSame = true;
                        break;
                    }
                }
                if (!findSame) {
                    List<String> list = new ArrayList<>();
                    list.add(str);
                    linkedList.add(list);
                }
            } else {
                List<List<String>> list = new ArrayList<>();
                List<String> childList = new ArrayList<>();
                childList.add(str);
                list.add(childList);
                map.put(sum, list);
            }
        }

        List<List<String>> result = new ArrayList<>();
        for (List<List<String>> list : map.values()) {
            for (List<String> childList : list) {
                result.add(childList);
            }
        }
        return result;
    }

    public static List<List<String>> groupAnagrams_2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] counts = new int[26];
            int length = str.length();
            for (int i = 0; i < length; i++) {
                counts[str.charAt(i) - 'a']++;
            }
            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    sb.append((char) ('a' + i));
                    sb.append(counts[i]);
                }
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }
}
