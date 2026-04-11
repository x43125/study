package com.zf.service.impl;

import com.zf.dto.CreateOrderDTO;
import com.zf.entity.Order;
import com.zf.entity.OrderItem;
import com.zf.mapper.OrderItemMapper;
import com.zf.mapper.OrderMapper;
import com.zf.mq.OrderMessage;
import com.zf.mq.OrderProducer;
import com.zf.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 订单Service实现
 * 分库分表改造提示：
 * 1. 分库分表后，事务管理需要特别注意
 * 2. 跨库事务需要使用分布式事务（如Seata）
 * 3. 统计查询可能需要在业务层聚合
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    /**
     * 默认未删除标记
     */
    private static final int NOT_DELETED = 0;

    /**
     * 订单编号前缀
     */
    private static final String ORDER_NO_PREFIX = "ORD";

    /**
     * 订单编号后缀随机数位数
     */
    private static final int ORDER_NO_SUFFIX_LENGTH = 4;

    /**
     * 订单编号后缀最大值
     */
    private static final int ORDER_NO_SUFFIX_MAX = 10000;

    /**
     * 订单明细数量范围
     */
    private static final int MIN_ITEM_COUNT = 1;
    private static final int MAX_ITEM_COUNT = 3;

    /**
     * 商品ID范围
     */
    private static final int MIN_PRODUCT_ID = 1;
    private static final int MAX_PRODUCT_ID = 100;

    /**
     * 商品价格范围
     */
    private static final int MIN_PRICE = 100;
    private static final int MAX_PRICE = 500;

    /**
     * 商品数量范围
     */
    private static final int MIN_QUANTITY = 1;
    private static final int MAX_QUANTITY = 5;

    /**
     * 订单初始状态
     */
    private static final int INITIAL_STATUS = 0;

    private final Random random = new Random();

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    /**
     * 订单消息生产者
     */
    @Autowired
    private OrderProducer orderProducer;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createOrder(Order order, List<OrderItem> orderItems) {
        orderMapper.insert(order);
        Long orderId = order.getId();

        for (OrderItem item : orderItems) {
            item.setOrderId(orderId);
            orderItemMapper.insert(item);
        }

        return orderId;
    }

    @Override
    public Order getOrderWithItems(Long orderId) {
        Order order = orderMapper.selectById(orderId);
        if (order == null) {
            return null;
        }

        List<OrderItem> items = orderItemMapper.selectByOrderId(orderId);
        // 可以在这里设置到order对象中，如果需要的话
        return order;
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return orderMapper.selectByUserId(userId);
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
        return orderMapper.selectCount();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createOrderWithDTO(CreateOrderDTO createOrderDTO) {
        Long userId = createOrderDTO.getUserId();

        Order order = new Order();
        order.setOrderNo(generateOrderNo());
        order.setUserId(userId);
        order.setUserName("用户" + userId);
        order.setStatus(INITIAL_STATUS);
        order.setReceiverName(createOrderDTO.getReceiverName());
        order.setReceiverPhone(createOrderDTO.getReceiverPhone());
        order.setReceiverAddress(createOrderDTO.getReceiverAddress());
        order.setCreateTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());
        order.setDeleted(NOT_DELETED);

        List<OrderItem> items = generateRandomOrderItems();
        BigDecimal totalAmount = items.stream()
                .map(OrderItem::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalAmount(totalAmount);
        Long orderId = createOrder(order, items);

        // 发送订单创建消息到 MQ
        OrderMessage orderMessage = OrderMessage.builder()
                .orderCode(order.getOrderNo())
                .userId(userId)
                .totalAmount(totalAmount)
                .status(order.getStatus())
                .address(order.getReceiverAddress())
                .orderTime(new java.util.Date())
                .messageType("ORDER_CREATE")
                .build();
        orderProducer.sendOrderMessage(orderMessage);

        return orderId;
    }

    @Override
    public String generateOrderNo() {
        return ORDER_NO_PREFIX + System.currentTimeMillis()
                + String.format("%0" + ORDER_NO_SUFFIX_LENGTH + "d", random.nextInt(ORDER_NO_SUFFIX_MAX));
    }

    /**
     * 生成随机订单明细
     */
    private List<OrderItem> generateRandomOrderItems() {
        List<OrderItem> items = new ArrayList<>();
        int itemCount = random.nextInt(MAX_ITEM_COUNT - MIN_ITEM_COUNT + 1) + MIN_ITEM_COUNT;

        for (int i = 0; i < itemCount; i++) {
            OrderItem item = new OrderItem();
            item.setProductId((long) (random.nextInt(MAX_PRODUCT_ID - MIN_PRODUCT_ID + 1) + MIN_PRODUCT_ID));
            item.setProductName("商品" + (i + 1));
            item.setPrice(new BigDecimal(random.nextInt(MAX_PRICE - MIN_PRICE + 1) + MIN_PRICE));
            item.setQuantity(random.nextInt(MAX_QUANTITY - MIN_QUANTITY + 1) + MIN_QUANTITY);
            item.setTotalAmount(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
            item.setCreateTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            item.setDeleted(NOT_DELETED);

            items.add(item);
        }

        return items;
    }
}