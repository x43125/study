package com.wx.base.juc.base;

import com.wx.base.juc.advance.utils.JUCUtils;
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
        // 取得下载的内容，如果还未下载则阻塞等待
        Thread t1 = new Thread(() -> {
            JUCUtils.sysout("获取下载内容，如果尚未下载，则阻塞此线程，先去下载");
            List<String> downloadContent = (List<String>) guidedObject.get();
            JUCUtils.sysout("获取到下载内容，装载");
            lines.set(downloadContent);
            JUCUtils.sleeper(10000000);
        }, "t1");

        new Thread(() -> {
            try {
                // 执行下载操作
                JUCUtils.sysout("下载...");
                List<String> content = Download.download();
                // 将下载结果保存起来
                JUCUtils.sysout("保存下载的内容");
                guidedObject.complete(content);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, "t2").start();

        t1.start();
        t1.join(5000);
        System.out.println(lines.get().size());
        lines.get().forEach(System.out::println);
    }
}
