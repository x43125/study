package com.zf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zf.entity.Order;
import com.zf.entity.OrderItem;

import java.util.List;

/**
 * 订单Service接口
 */
public interface OrderService extends IService<Order> {

    /**
     * 创建订单（含明细）
     */
    Long createOrder(Order order, List<OrderItem> orderItems);

    /**
     * 查询订单详情（含明细）
     */
    Order getOrderWithItems(Long orderId);

    /**
     * 查询用户订单列表
     */
    List<Order> getUserOrders(Long userId);

    /**
     * 更新订单状态
     */
    boolean updateOrderStatus(Long orderId, Integer status);

    /**
     * 统计订单数量
     */
    long countOrders();

    /**
     * 批量插入订单（用于快速生成测试数据）
     */
    void batchInsertOrders(List<Order> orders);

    /**
     * 批量插入订单明细（用于快速生成测试数据）
     */
    void batchInsertOrderItems(List<OrderItem> items);
}
