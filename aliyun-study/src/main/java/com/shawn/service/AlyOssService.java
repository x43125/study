package com.shawn.service;

/**
 * @author wangxiang
 * @date 2024/3/27 22:56
 * @description
 */
public interface AlyOssService {
    void upload(String bucketName, String objectName, String path);
}
