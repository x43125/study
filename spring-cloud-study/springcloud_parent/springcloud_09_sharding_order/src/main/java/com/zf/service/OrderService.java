package com.zf.service;

import com.zf.dto.CreateOrderDTO;
import com.zf.entity.Order;
import com.zf.entity.OrderItem;

import java.util.List;

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
     * 生成订单编号
     */
    String generateOrderNo();
}