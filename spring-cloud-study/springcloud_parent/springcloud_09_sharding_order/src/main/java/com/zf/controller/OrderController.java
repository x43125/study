package com.zf.controller;

import com.zf.entity.Order;
import com.zf.entity.OrderItem;
import com.zf.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 订单Controller
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     * POST /order/create
     */
    @PostMapping("/create")
    public Map<String, Object> createOrder(@RequestBody Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();

        try {
            Long userId = Long.valueOf(params.get("userId").toString());
            String receiverName = params.get("receiverName").toString();
            String receiverPhone = params.get("receiverPhone").toString();
            String receiverAddress = params.get("receiverAddress").toString();

            // 创建订单
            Order order = new Order();
            order.setOrderNo(generateOrderNo());
            order.setUserId(userId);
            order.setUserName("用户" + userId);
            order.setStatus(0);
            order.setReceiverName(receiverName);
            order.setReceiverPhone(receiverPhone);
            order.setReceiverAddress(receiverAddress);
            order.setCreateTime(LocalDateTime.now());
            order.setUpdateTime(LocalDateTime.now());

            // 创建订单明细（模拟数据）
            List<OrderItem> items = new ArrayList<>();
            int itemCount = new Random().nextInt(3) + 1; // 1-3个商品
            BigDecimal totalAmount = BigDecimal.ZERO;

            for (int i = 0; i < itemCount; i++) {
                OrderItem item = new OrderItem();
                item.setProductId((long) (new Random().nextInt(100) + 1));
                item.setProductName("商品" + (i + 1));
                item.setPrice(new BigDecimal(new Random().nextInt(500) + 100));
                item.setQuantity(new Random().nextInt(5) + 1);
                item.setTotalAmount(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
                item.setCreateTime(System.currentTimeMillis());
                item.setUpdateTime(System.currentTimeMillis());
                item.setDeleted(0);
                
                totalAmount = totalAmount.add(item.getTotalAmount());
                items.add(item);
            }

            order.setTotalAmount(totalAmount);

            Long orderId = orderService.createOrder(order, items);

            result.put("success", true);
            result.put("message", "订单创建成功");
            result.put("data", orderId);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "订单创建失败：" + e.getMessage());
        }

        return result;
    }

    /**
     * 查询订单详情
     * GET /order/{id}
     */
    @GetMapping("/{id}")
    public Map<String, Object> getOrder(@PathVariable Long id) {
        Map<String, Object> result = new HashMap<>();

        try {
            Order order = orderService.getOrderWithItems(id);
            if (order == null) {
                result.put("success", false);
                result.put("message", "订单不存在");
            } else {
                result.put("success", true);
                result.put("data", order);
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "查询失败：" + e.getMessage());
        }

        return result;
    }

    /**
     * 查询用户订单列表
     * GET /order/user/{userId}
     */
    @GetMapping("/user/{userId}")
    public Map<String, Object> getUserOrders(@PathVariable Long userId) {
        Map<String, Object> result = new HashMap<>();

        try {
            List<Order> orders = orderService.getUserOrders(userId);
            result.put("success", true);
            result.put("data", orders);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "查询失败：" + e.getMessage());
        }

        return result;
    }

    /**
     * 更新订单状态
     * PUT /order/{id}/status
     */
    @PutMapping("/{id}/status")
    public Map<String, Object> updateOrderStatus(@PathVariable Long id, @RequestParam Integer status) {
        Map<String, Object> result = new HashMap<>();

        try {
            boolean success = orderService.updateOrderStatus(id, status);
            result.put("success", success);
            result.put("message", success ? "状态更新成功" : "状态更新失败");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "更新失败：" + e.getMessage());
        }

        return result;
    }

    /**
     * 统计订单数量
     * GET /order/count
     */
    @GetMapping("/count")
    public Map<String, Object> countOrders() {
        Map<String, Object> result = new HashMap<>();

        try {
            long count = orderService.countOrders();
            result.put("success", true);
            result.put("data", count);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "统计失败：" + e.getMessage());
        }

        return result;
    }

    /**
     * 批量生成订单数据（用于测试）
     * POST /order/batch
     */
    @PostMapping("/batch")
    public Map<String, Object> batchCreateOrders(@RequestParam(defaultValue = "100") int count) {
        Map<String, Object> result = new HashMap<>();

        try {
            int successCount = 0;
            int userCount = 10; // 10个用户

            for (int i = 0; i < count; i++) {
                try {
                    Long userId = (long) (new Random().nextInt(userCount) + 1);

                    Order order = new Order();
                    order.setOrderNo(generateOrderNo());
                    order.setUserId(userId);
                    order.setUserName("用户" + userId);
                    order.setStatus(new Random().nextInt(5));
                    order.setReceiverName("收货人" + userId);
                    order.setReceiverPhone("13800138000");
                    order.setReceiverAddress("北京市朝阳区");
                    order.setCreateTime(LocalDateTime.now().minusDays(new Random().nextInt(30)));
                    order.setUpdateTime(LocalDateTime.now());

                    List<OrderItem> items = new ArrayList<>();
                    int itemCount = new Random().nextInt(3) + 1;
                    BigDecimal totalAmount = BigDecimal.ZERO;

                    for (int j = 0; j < itemCount; j++) {
                        OrderItem item = new OrderItem();
                        item.setProductId((long) (new Random().nextInt(100) + 1));
                        item.setProductName("商品" + (j + 1));
                        item.setPrice(new BigDecimal(new Random().nextInt(500) + 100));
                        item.setQuantity(new Random().nextInt(5) + 1);
                        item.setTotalAmount(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
                        item.setCreateTime(System.currentTimeMillis());
                        item.setUpdateTime(System.currentTimeMillis());
                        item.setDeleted(0);

                        totalAmount = totalAmount.add(item.getTotalAmount());
                        items.add(item);
                    }

                    order.setTotalAmount(totalAmount);
                    orderService.createOrder(order, items);
                    successCount++;

                } catch (Exception e) {
                    System.err.println("创建订单失败：" + e.getMessage());
                }
            }

            result.put("success", true);
            result.put("message", String.format("批量创建完成，成功：%d，失败：%d", successCount, count - successCount));
            result.put("data", successCount);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "批量创建失败：" + e.getMessage());
        }

        return result;
    }

    /**
     * 快速批量插入订单数据（使用批量插入，性能更好）
     * POST /order/batch-insert
     */
    @PostMapping("/batch-insert")
    public Map<String, Object> batchInsertOrders(@RequestParam(defaultValue = "10000") int count) {
        Map<String, Object> result = new HashMap<>();
        long startTime = System.currentTimeMillis();

        try {
            int userCount = 100; // 100个用户
            
            // 生成订单数据
            List<Order> orders = new ArrayList<>();
            List<OrderItem> allItems = new ArrayList<>();
            
            for (int i = 0; i < count; i++) {
                Long userId = (long) (new Random().nextInt(userCount) + 1);
                
                Order order = new Order();
                order.setOrderNo(generateOrderNo());
                order.setUserId(userId);
                order.setUserName("用户" + userId);
                order.setStatus(new Random().nextInt(5));
                order.setReceiverName("收货人" + userId);
                order.setReceiverPhone("13800138000");
                order.setReceiverAddress("北京市朝阳区");
                order.setCreateTime(LocalDateTime.now().minusDays(new Random().nextInt(30)));
                order.setUpdateTime(LocalDateTime.now());
                order.setDeleted(0);
                
                int itemCount = new Random().nextInt(3) + 1; // 1-3个商品
                BigDecimal totalAmount = BigDecimal.ZERO;
                
                for (int j = 0; j < itemCount; j++) {
                    OrderItem item = new OrderItem();
                    item.setProductId((long) (new Random().nextInt(100) + 1));
                    item.setProductName("商品" + (j + 1));
                    item.setPrice(new BigDecimal(new Random().nextInt(500) + 100));
                    item.setQuantity(new Random().nextInt(5) + 1);
                    item.setTotalAmount(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
                    item.setCreateTime(System.currentTimeMillis());
                    item.setUpdateTime(System.currentTimeMillis());
                    item.setDeleted(0);
                    
                    totalAmount = totalAmount.add(item.getTotalAmount());
                    allItems.add(item);
                }
                
                order.setTotalAmount(totalAmount);
                orders.add(order);
            }
            
            // 批量插入订单
            orderService.batchInsertOrders(orders);
            
            // 设置订单明细的订单ID并批量插入
            for (int i = 0; i < orders.size(); i++) {
                Long orderId = orders.get(i).getId();
                // 找到属于这个订单的明细
                int itemStart = i * 2; // 假设每个订单平均2个明细，简化处理
                // 实际应该用其他方式关联，这里为了简化，重新生成明细数据
            }
            
            // 简化处理：只插入订单，不插入明细
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            
            result.put("success", true);
            result.put("message", String.format("批量插入完成：%d条订单，耗时：%d秒", count, duration / 1000));
            result.put("data", count);
            result.put("duration", duration + "ms");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "批量插入失败：" + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 生成订单编号
     */
    private String generateOrderNo() {
        return "ORD" + System.currentTimeMillis() + String.format("%04d", new Random().nextInt(10000));
    }
}