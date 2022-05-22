package com.wx.common.api;

/**
 * 枚举了一些常用API操作码
 * @author macro
 * @date 2019/4/19
 */
public enum ResultCode implements IErrorCode {
    /**
     * 错误码标志：错误码，错误码对应信息
     */
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),

    /**
     * 业务添加
     */
    USER_PWD_FAILED(601, "用户名或密码错误");

    private long code;
    private String message;

    /**
     * 构造函数
     * @param code 错误码
     * @param message 错误信息
     */
    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
