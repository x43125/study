package com.wx.redis.study;

import com.wx.redis.entity.Club;
import com.wx.redis.util.ProtostuffSerializer;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisClientConfig;
import redis.clients.jedis.Pipeline;

import java.util.Date;
import java.util.List;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/12/18
 */
public class RedisStudy01 {
    private static String host = "47.98.59.193";
    private static int port = 6379;
    private static String password = "123ABCdef*";

    public static void connect01() {

        Jedis jedis = new Jedis(host, port, 1200, false);
        jedis.auth(password);

        String setResult = jedis.set("hello", "world");
        System.out.println("setResult = " + setResult);
        Long setnxResult = jedis.setnx("hello", "redis");
        System.out.println("setnxResult = " + setnxResult);

        String getResult = jedis.get("hello");
        System.out.println("getResult = " + getResult);

        jedis.close();
    }

    public static void connect02() {
        try (Jedis jedis = new Jedis(host, port, 1200, false)) {
            jedis.auth(password);
            Long setnxRes = jedis.setnx("hello", "world");
            System.out.println("setnxRes = " + setnxRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void connect03() {
        try (Jedis jedis = new Jedis(host, port, 1200, false)) {
            jedis.auth(password);
            // 1.string
            jedis.set("hello", "world");
            System.out.println("jedis.get(\"hello\") = " + jedis.get("hello"));
            // 2.hash
            jedis.hset("myHash", "hashKey1", "hashValue1");
            jedis.hset("myHash", "hashKey2", "hashValue2");
            System.out.println("jedis.hgetAll(\"myHash\") = " + jedis.hgetAll("myHash"));
            // 3.list
            jedis.rpush("myList", "1");
            jedis.rpush("myList", "2");
            jedis.rpush("myList", "3");
            jedis.lrange("myList", 0, -1);
            // 4.set
            jedis.sadd("mySet", "a");
            jedis.sadd("mySet", "b");
            jedis.sadd("mySet", "c");
            System.out.println("jedis.smembers(\"mySet\") = " + jedis.smembers("mySet"));
            // 5.zset
            jedis.zadd("myZSet", 99, "tom");
            jedis.zadd("myZSet", 66, "peter");
            jedis.zadd("myZSet", 33, "james");
            System.out.println("jedis.zrangeWithScores(\"myZSet\", 0, -1) = " + jedis.zrangeWithScores("myZSet", 0, -1));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void connect04() {
        ProtostuffSerializer protostuffSerializer = new ProtostuffSerializer();

        Jedis jedis = new Jedis(host, port, 1200, false);
        jedis.auth(password);

        String key = "club:1";
        Club club = new Club(1, "AC", "米兰", new Date(), 1);
        // 序列化
        byte[] clubBytes = protostuffSerializer.serialize(club);
        jedis.set(key.getBytes(), clubBytes);
        // 反序列化
        byte[] resultBytes = jedis.get(key.getBytes());
        Club deserializeClub = protostuffSerializer.deserialize(resultBytes);

        System.out.println(deserializeClub);

    }

    public static void pipelineTest() {
        Jedis jedis = new Jedis(host, port);
        jedis.auth(password);

        System.out.println("jedis.get(\"hello\") = " + jedis.get("hello"));
        System.out.println("jedis.get(\"key1\") = " + jedis.get("key1"));
        Pipeline pipeline = jedis.pipelined();
        String[] keys = {"hello", "key1"};
        for (String key : keys) {
            pipeline.del(key);
        }
        System.out.println("开始执行命令");
        pipeline.sync();
        System.out.println("jedis.get(\"hello\") = " + jedis.get("hello"));
        System.out.println("jedis.get(\"key1\") = " + jedis.get("key1"));
    }

    public static void pipelineTest_2() {
        Jedis jedis = new Jedis(host, port);
        jedis.auth(password);
        Pipeline pipeline = jedis.pipelined();
        pipeline.set("hello", "world");
        pipeline.incr("counter");
        List<Object> resultList = pipeline.syncAndReturnAll();
        resultList.forEach(System.out::println);
    }

    public static void main(String[] args) {
        connect01();
//        connect02();
//        connect03();
//        connect04();
//        pipelineTest();
//        pipelineTest_2();
    }
}
