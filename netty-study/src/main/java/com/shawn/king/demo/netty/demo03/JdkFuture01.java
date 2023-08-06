package com.shawn.king.demo.netty.demo03;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Shawn
 * @date 2023/8/6 12:59
 * @description
 */
@Slf4j
public class JdkFuture01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(1);
        log.debug("submit...");
        Future<Integer> future = service.submit(() -> {
            log.debug("executing...");
            Thread.sleep(1000);
            return 50;
        });

        log.debug("waiting...");
        log.debug("future: {}", future.get());
    }
}
