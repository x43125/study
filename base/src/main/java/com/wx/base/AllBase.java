package com.wx.base;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import com.wx.base.AllBase.Runn2;

public class AllBase {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // TreeMap<Integer, String> map = new TreeMap();
        // map.put(2, "world");
        // map.put(1, "world");
        // map.put(3, "world");

        // System.out.println(map);

        // HashMap map2 = new HashMap<>();
        // ConcurrentHashMap map3 = new ConcurrentHashMap<>();
        // map3.put(1, "world");
        // Hashtable map4 = new Hashtable<>();

        // map4.put(1, "world");

        // new Runn2().start();

        // new Thread(() -> {
        //     System.out.println("1.继承Thread创建线程");
        // }).start();

        // System.out.println("主线程");
        // ExecutorService service = Executors.newCachedThreadPool();
        // Future future = service.submit(new Runn());
        // System.out.println(future.get());

        // Executor executor = new ThreadPoolExecutor(0, 0, 0, null, null);
        Runn2 runn2 = new Runn2();
        runn2.start();
        runn2.start();
    }

    static class Runn implements Callable {

        @Override
        public String call() throws Exception {
            System.out.println("1.实现Callable接口创建线程，可以通过FutureTask 获取返回值");
            return "返回值";
        }

    }

    static class Runn2 extends Thread {
        @Override
        public void run() {
            System.out.println("2.继承Thread类创建线程");
        }
    }
}
