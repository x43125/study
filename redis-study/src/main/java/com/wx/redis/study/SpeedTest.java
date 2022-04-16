package com.wx.redis.study;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanResult;

import java.util.List;

/**
 * @Author: x43125
 * @Date: 22/02/09
 */
public class SpeedTest {

    public static void main(String[] args) {
        String host = "47.98.59.193";
        int port = 6379;
        String passwd = "123ABCdef*";

        int num = 10000;

        try (Jedis jedis = new Jedis(host, port)) {
            jedis.auth(passwd);

            String[] msetArr = new String[num * 2];
            for (int i = 0; i < num; i++) {
                msetArr[i * 2] = "hello" + (num + i);
                msetArr[i * 2 + 1] = "world" + (num + i);
            }

            jedis.mset(msetArr);

            String cur = "0";
            int count = 0;
            do {
                ScanResult<String> scan = jedis.scan(cur);
                cur = scan.getCursor();
                List<String> result = scan.getResult();
                result.forEach(System.out::println);
                count += result.size();
            } while (!"0".equals(cur));
            System.out.println("count: " + count);
            System.out.println("success!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
