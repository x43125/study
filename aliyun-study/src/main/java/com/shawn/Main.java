package com.shawn;

import com.shawn.service.AlyOssService;
import com.shawn.service.impl.AlyOssServiceImpl;

public class Main {
    public static void main(String[] args) {
        AlyOssService alyOssService = new AlyOssServiceImpl();
        alyOssService.upload("shawn-oss", "test.txt", "D:\\test.txt");

    }
}