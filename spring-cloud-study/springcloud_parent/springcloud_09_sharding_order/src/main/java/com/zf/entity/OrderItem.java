package com.zf.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单明细实体
 * 分库分表改造提示：
 * 1. orderId适合作为分库分表键，与Order表保持一致
 * 2. OrderItem应该与Order配置为绑定表（Binding Table）
 *    确保同一订单的订单明细和订单在同一个分库分表中，避免跨库关联查询
 */
@Data
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 明细ID - 主键
     */
    private Long id;

    /**
     * 订单编码 - 雪花算法生成，用作分片键
     * 与Order表的code保持一致，用于绑定表关联
     */
    private Long code;

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

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;

    /**
     * 逻辑删除：0-未删除 1-已删除
     */
    private Integer deleted;
}