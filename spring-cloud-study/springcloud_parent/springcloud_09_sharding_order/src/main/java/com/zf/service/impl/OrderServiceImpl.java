package com.zf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zf.entity.Order;
import com.zf.entity.OrderItem;
import com.zf.mapper.OrderItemMapper;
import com.zf.mapper.OrderMapper;
import com.zf.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单Service实现
 * 分库分表改造提示：
 * 1. 分库分表后，事务管理需要特别注意
 * 2. 跨库事务需要使用分布式事务（如Seata）
 * 3. 统计查询可能需要在业务层聚合
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createOrder(Order order, List<OrderItem> orderItems) {
        // 保存订单
        orderMapper.insert(order);
        Long orderId = order.getId();

        // 保存订单明细
        for (OrderItem item : orderItems) {
            item.setOrderId(orderId);
            orderItemMapper.insert(item);
        }

        return orderId;
    }

    @Override
    public Order getOrderWithItems(Long orderId) {
        // 查询订单
        Order order = orderMapper.selectById(orderId);
        if (order == null) {
            return null;
        }

        // 查询订单明细
        LambdaQueryWrapper<OrderItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderItem::getOrderId, orderId);
        List<OrderItem> items = orderItemMapper.selectList(wrapper);

        return order;
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Order::getUserId, userId);
        wrapper.orderByDesc(Order::getCreateTime);
        return orderMapper.selectList(wrapper);
    }

    @Override
    public boolean updateOrderStatus(Long orderId, Integer status) {
        Order order = new Order();
        order.setId(orderId);
        order.setStatus(status);
        return orderMapper.updateById(order) > 0;
    }

    @Override
    public long countOrders() {
        return orderMapper.selectCount(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchInsertOrders(List<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            return;
        }
        // 分批插入，每批1000条
        int batchSize = 1000;
        int total = orders.size();
        for (int i = 0; i < total; i += batchSize) {
            int end = Math.min(i + batchSize, total);
            List<Order> batch = orders.subList(i, end);
            orderMapper.batchInsert(batch);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchInsertOrderItems(List<OrderItem> items) {
        if (items == null || items.isEmpty()) {
            return;
        }
        // 分批插入，每批1000条
        int batchSize = 1000;
        int total = items.size();
        for (int i = 0; i < total; i += batchSize) {
            int end = Math.min(i + batchSize, total);
            List<OrderItem> batch = items.subList(i, end);
            orderItemMapper.batchInsert(batch);
        }
    }
}
