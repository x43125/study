package com.zf.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * 统一响应DTO
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 额外信息
     */
    private Object extra;

    public ResponseDTO() {
    }

    public ResponseDTO(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResponseDTO(Boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseDTO<T> success() {
        return new ResponseDTO<>(true, "操作成功");
    }

    public static <T> ResponseDTO<T> success(String message) {
        return new ResponseDTO<>(true, message);
    }

    public static <T> ResponseDTO<T> success(T data) {
        return new ResponseDTO<>(true, "操作成功", data);
    }

    public static <T> ResponseDTO<T> success(String message, T data) {
        return new ResponseDTO<>(true, message, data);
    }

    public static <T> ResponseDTO<T> error() {
        return new ResponseDTO<>(false, "操作失败");
    }

    public static <T> ResponseDTO<T> error(String message) {
        return new ResponseDTO<>(false, message);
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", extra=" + extra +
                '}';
    }
}