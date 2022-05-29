package com.wx.algorithm.leetcode.contest.weekend.w0529;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Descrption: todo 需要考虑好多东西，未AC
 * @Author: x43125
 * @Date: 22/05/29
 */
public class DiscountPrices {
    public static void main(String[] args) {
        String sentence = "there are $1 $2 and 5$ candies in the shop";
        int discount = 50;
        // "there are $0.50 $1.00 and 5$ candies in the shop";
        DiscountPrices prices = new DiscountPrices();
        String res = prices.discountPrice(sentence, discount);
        System.out.println("res = " + res);
    }

    public String discountPrices(String sentence, int discount) {
        char[] chars = sentence.toCharArray();
        double disDouble = 1 - discount / 100.00;

        boolean start = false;
        StringBuilder sb = new StringBuilder();
        Map<String, String> map = new HashMap<>();
        Map<String, List<int[]>> index = new HashMap<>();
        int startI = -1, endI = -1;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '$') {
                start = true;
                startI = i;
                continue;
            }
            if (start) {
                if (chars[i] < '0' || chars[i] > '9') {
                    endI = i;
                    start = false;
                    if (sb.length() > 0) {
                        String s = sb.toString();
                        double f = Double.parseDouble(s);
                        f *= disDouble;

                        int[] temp = {startI + 1, endI - 1};
                        List<int[]> list = index.getOrDefault(s, new ArrayList<>());
                        list.add(temp);
                        index.put(s, list);

                        map.put(s, '$' + String.valueOf(f));
                    }
                    sb = new StringBuilder();
                } else {
                    sb.append(chars[i]);
                }
            }
        }

        String res = sentence;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            res = res.replace(entry.getKey(), entry.getValue());
        }

        return res;
    }

    /**
     * 正则表达式
     * @param sentence
     * @param discount
     * @return
     */
    private String discountPrice(String sentence, int discount) {
        return Stream.of(sentence.split(" "))
                .map(
                        t -> !t.matches("\\$\\d+") ?
                        t : String.format("$%.2f", Long.parseLong(t.substring(1)) * (1 - discount / 100.))
                )
                .collect(Collectors.joining(" "));
    }
}
