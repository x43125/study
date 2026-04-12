package com.zf.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单实体
 * 分库分表改造提示：
 * 1. userId适合作为分库键（按用户查询订单是常见场景）
 * 2. orderId适合作为分表键（订单ID是唯一标识）
 */
@Data
public class Order extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 订单编号 - 雪花算法生成
     */
    private Long orderNo;

    /**
     * 用户ID - 分库键
     * 分库建议：使用用户ID进行取模分库
     */
    private Long userId;

    /**
     * 用户昵称（冗余字段，减少关联查询）
     */
    private String userName;

    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;

    /**
     * 订单状态：0-待支付 1-已支付 2-已发货 3-已完成 4-已取消
     */
    private Integer status;

    /**
     * 收货地址ID
     */
    private Long addressId;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 收货人电话
     */
    private String receiverPhone;

    /**
     * 收货地址
     */
    private String receiverAddress;

    /**
     * 支付时间
     */
    private LocalDateTime payTime;

    /**
     * 发货时间
     */
    private LocalDateTime shipTime;

    /**
     * 完成时间
     */
    private LocalDateTime finishTime;
}