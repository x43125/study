package com.wx.common.api;

/**
 * 封装API的错误码
 *
 * @author macro
 * @date 2019/4/19
 */
public interface IErrorCode {
    /**
     * 获取错误code
     * @return
     */
    long getCode();

    /**
     * 获取报错信息
     * @return
     */
    String getMessage();
}
