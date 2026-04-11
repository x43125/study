package com.zf.mq;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单消息实体
 * 用于 RocketMQ 消息传递
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单编号（雪花算法生成）
     */
    private Long orderNo;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;

    /**
     * 订单状态
     * 0-待支付 1-已支付 2-已发货 3-已完成 4-已取消
     */
    private Integer status;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 下单时间
     */
    private Date orderTime;

    /**
     * 消息类型
     * ORDER_CREATE-订单创建
     * ORDER_PAY-订单支付
     * ORDER_SHIP-订单发货
     */
    private String messageType;

    /**
     * 消息发送时间
     */
    private Date sendTime;
}