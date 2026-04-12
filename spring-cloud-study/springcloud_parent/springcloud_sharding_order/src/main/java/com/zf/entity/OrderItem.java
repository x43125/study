package com.zf.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单明细实体
 * 分库分表改造提示：
 * 1. orderId适合作为分库分表键，与Order表保持一致
 * 2. OrderItem应该与Order配置为绑定表（Binding Table）
 *    确保同一订单的订单明细和订单在同一个分库分表中，避免跨库关联查询
 */
@Data
public class OrderItem extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID - 关联订单表
     * 分片键建议：与Order表的分片策略保持一致
     */
    private Long orderId;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 商品名称（冗余字段，减少关联查询）
     */
    private String productName;

    /**
     * 商品图片URL（冗余字段）
     */
    private String productImg;

    /**
     * 商品单价
     */
    private BigDecimal price;

    /**
     * 购买数量
     */
    private Integer quantity;

    /**
     * 小计金额（price * quantity）
     */
    private BigDecimal totalAmount;
}