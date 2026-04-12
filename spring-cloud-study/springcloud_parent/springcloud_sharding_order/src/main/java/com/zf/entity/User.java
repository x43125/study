package com.zf.entity;

import lombok.Data;

/**
 * 用户实体
 * 分库分表改造提示：
 * 1. User表通常不需要分库分表（数据量相对较小）
 * 2. 如果需要分库，可以使用userId作为分片键
 * 3. 也可以考虑将User表作为单表放在独立的用户库中
 */
@Data
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像URL
     */
    private String avatar;

    /**
     * 用户状态：0-正常 1-禁用
     */
    private Integer status;
}