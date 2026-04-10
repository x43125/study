package com.zf.service.impl;

import com.zf.dto.CreateOrderDTO;
import com.zf.entity.Order;
import com.zf.entity.OrderItem;
import com.zf.mapper.OrderItemMapper;
import com.zf.mapper.OrderMapper;
import com.zf.service.OrderService;
import com.zf.util.CodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
     * 批量插入的批次大小
     */
    private static final int BATCH_SIZE = 1000;

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
     * 测试用户数量
     */
    private static final int TEST_USER_COUNT = 10;

    /**
     * 批量生成测试用户数量
     */
    private static final int BATCH_USER_COUNT = 100;

    /**
     * 历史订单天数范围
     */
    private static final int HISTORY_DAYS = 30;

    /**
     * 订单状态范围
     */
    private static final int MAX_STATUS = 5;

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
     * 雪花算法ID生成器
     */
    @Autowired
    private CodeGenerator codeGenerator;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createOrder(Order order, List<OrderItem> orderItems) {
        Long code = codeGenerator.nextId();
        order.setCode(code);
        
        log.info("生成订单code: {}", code);
        
        orderMapper.insert(order);
        Long orderId = order.getId();

        for (OrderItem item : orderItems) {
            item.setCode(code);
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

        orderItemMapper.selectByOrderId(orderId);
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
    public void batchInsertOrders(List<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            return;
        }
        int total = orders.size();
        for (int i = 0; i < total; i += BATCH_SIZE) {
            int end = Math.min(i + BATCH_SIZE, total);
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
        int total = items.size();
        for (int i = 0; i < total; i += BATCH_SIZE) {
            int end = Math.min(i + BATCH_SIZE, total);
            List<OrderItem> batch = items.subList(i, end);
            orderItemMapper.batchInsert(batch);
        }
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

        List<OrderItem> items = generateRandomOrderItems();
        BigDecimal totalAmount = items.stream()
                .map(OrderItem::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalAmount(totalAmount);
        return createOrder(order, items);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchCreateOrders(int count) {
        int successCount = 0;
        for (int i = 0; i < count; i++) {
            try {
                Long userId = (long) (random.nextInt(TEST_USER_COUNT) + 1);
                Order order = createRandomOrder(userId);
                List<OrderItem> items = generateRandomOrderItems();
                
                BigDecimal totalAmount = items.stream()
                        .map(OrderItem::getTotalAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                order.setTotalAmount(totalAmount);
                
                createOrder(order, items);
                successCount++;
            } catch (Exception e) {
                log.error("创建订单失败：{}", e.getMessage());
            }
        }
        return successCount;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchInsertOrdersWithItems(List<Order> orders, List<OrderItem> items) {
        batchInsertOrders(orders);
        batchInsertOrderItems(items);
    }

    @Override
    public String generateOrderNo() {
        return ORDER_NO_PREFIX + System.currentTimeMillis() 
                + String.format("%0" + ORDER_NO_SUFFIX_LENGTH + "d", random.nextInt(ORDER_NO_SUFFIX_MAX));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> batchGenerateOrders(int count) {
        Map<String, Object> result = new HashMap<>();
        long startTime = System.currentTimeMillis();

        try {
            List<Order> orders = new ArrayList<>();
            List<OrderItem> allItems = new ArrayList<>();
            
            for (int i = 0; i < count; i++) {
                Long userId = (long) (random.nextInt(BATCH_USER_COUNT) + 1);
                Order order = createRandomOrder(userId);
                
                List<OrderItem> items = generateRandomOrderItems();
                BigDecimal totalAmount = items.stream()
                        .map(OrderItem::getTotalAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                order.setTotalAmount(totalAmount);
                
                allItems.addAll(items);
                orders.add(order);
            }
            
            batchInsertOrders(orders);
            batchInsertOrderItems(allItems);
            
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            
            result.put("success", true);
            result.put("message", String.format("批量插入完成：%d条订单，耗时：%d秒", count, duration / 1000));
            result.put("data", count);
            result.put("duration", duration + "ms");
        } catch (Exception e) {
            log.error("批量插入失败：{}", e.getMessage(), e);
            result.put("success", false);
            result.put("message", "批量插入失败：" + e.getMessage());
        }

        return result;
    }

    /**
     * 创建随机订单
     */
    private Order createRandomOrder(Long userId) {
        Order order = new Order();
        order.setOrderNo(generateOrderNo());
        order.setUserId(userId);
        order.setUserName("用户" + userId);
        order.setStatus(random.nextInt(MAX_STATUS));
        order.setReceiverName("收货人" + userId);
        order.setReceiverPhone("13800138000");
        order.setReceiverAddress("北京市朝阳区");
        order.setCreateTime(LocalDateTime.now().minusDays(random.nextInt(HISTORY_DAYS)));
        order.setUpdateTime(LocalDateTime.now());
        order.setDeleted(NOT_DELETED);
        return order;
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
            item.setCreateTime(System.currentTimeMillis());
            item.setUpdateTime(System.currentTimeMillis());
            item.setDeleted(NOT_DELETED);
            
            items.add(item);
        }
        
        return items;
    }
}