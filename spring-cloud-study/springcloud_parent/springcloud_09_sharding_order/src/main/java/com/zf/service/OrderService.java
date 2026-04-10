package com.zf.service;

import com.zf.dto.CreateOrderDTO;
import com.zf.entity.Order;
import com.zf.entity.OrderItem;

import java.util.List;
import java.util.Map;

/**
 * 订单Service接口
 */
public interface OrderService {

    /**
     * 创建订单（含明细）
     */
    Long createOrder(Order order, List<OrderItem> orderItems);

    /**
     * 根据DTO创建订单
     */
    Long createOrderWithDTO(CreateOrderDTO createOrderDTO);

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
     * 批量创建订单（生成随机数据）
     */
    int batchCreateOrders(int count);

    /**
     * 批量插入订单（用于快速生成测试数据）
     */
    void batchInsertOrders(List<Order> orders);

    /**
     * 批量插入订单和明细（用于快速生成测试数据）
     */
    void batchInsertOrdersWithItems(List<Order> orders, List<OrderItem> items);

    /**
     * 批量生成订单数据（用于性能测试）
     */
    Map<String, Object> batchGenerateOrders(int count);

    /**
     * 批量插入订单明细（用于快速生成测试数据）
     */
    void batchInsertOrderItems(List<OrderItem> items);

    /**
     * 生成订单编号
     */
    String generateOrderNo();
}
