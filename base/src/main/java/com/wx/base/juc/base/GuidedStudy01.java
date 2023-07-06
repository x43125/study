package com.wx.base.juc.base;

import com.wx.base.juc.base.model.GuidedObject;
import com.wx.base.juc.base.util.Download;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author wangxiang
 * @date 2023/7/6 14:42
 * @description
 */
public class GuidedStudy01 {
    public static void main(String[] args) throws InterruptedException {
        GuidedObject guidedObject = new GuidedObject();
        AtomicReference<List<String>> lines = new AtomicReference<>();
        Thread t1 = new Thread(() -> {
            lines.set((List<String>) guidedObject.get());
        }, "t1");

        new Thread(() -> {
            try {
                List<String> content = Download.download();
                guidedObject.complete(content);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, "t2").start();

        t1.start();
        t1.join();
        System.out.println(lines.get().size());
        lines.get().forEach(System.out::println);

    }
}
