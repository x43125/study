package com.zf.controller;

import com.zf.dto.CreateOrderDTO;
import com.zf.dto.ResponseDTO;
import com.zf.entity.Order;
import com.zf.mq.OrderMessage;
import com.zf.mq.OrderProducer;
import com.zf.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 订单Controller
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderProducer orderProducer;

    /**
     * 创建订单
     * POST /order/create
     */
    @PostMapping("/create")
    public ResponseDTO<Long> createOrder(@Valid @RequestBody CreateOrderDTO createOrderDTO) {
        try {
            Long orderId = orderService.createOrderWithDTO(createOrderDTO);
            return ResponseDTO.success("订单创建成功", orderId);
        } catch (Exception e) {
            return ResponseDTO.error("订单创建失败：" + e.getMessage());
        }
    }

    /**
     * 查询订单详情
     * GET /order/{id}
     */
    @GetMapping("/{id}")
    public ResponseDTO<Order> getOrder(@PathVariable Long id) {
        try {
            Order order = orderService.getOrderWithItems(id);
            if (order == null) {
                return ResponseDTO.error("订单不存在");
            } else {
                return ResponseDTO.success(order);
            }
        } catch (Exception e) {
            return ResponseDTO.error("查询失败：" + e.getMessage());
        }
    }

    /**
     * 查询用户订单列表
     * GET /order/user/{userId}
     */
    @GetMapping("/user/{userId}")
    public ResponseDTO<List<Order>> getUserOrders(@PathVariable Long userId) {
        try {
            List<Order> orders = orderService.getUserOrders(userId);
            return ResponseDTO.success(orders);
        } catch (Exception e) {
            return ResponseDTO.error("查询失败：" + e.getMessage());
        }
    }

    /**
     * 更新订单状态
     * PUT /order/{id}/status
     */
    @PutMapping("/{id}/status")
    public ResponseDTO<Void> updateOrderStatus(@PathVariable Long id, @RequestParam Integer status) {
        try {
            boolean success = orderService.updateOrderStatus(id, status);
            if (success) {
                return ResponseDTO.success("状态更新成功");
            } else {
                return ResponseDTO.error("状态更新失败");
            }
        } catch (Exception e) {
            return ResponseDTO.error("更新失败：" + e.getMessage());
        }
    }

    /**
     * 统计订单数量
     * GET /order/count
     */
    @GetMapping("/count")
    public ResponseDTO<Long> countOrders() {
        try {
            long count = orderService.countOrders();
            return ResponseDTO.success(count);
        } catch (Exception e) {
            return ResponseDTO.error("统计失败：" + e.getMessage());
        }
    }

    /**
     * 批量生成订单数据（用于测试）
     * POST /order/batch
     */
    @PostMapping("/batch")
    public ResponseDTO<Integer> batchCreateOrders(@RequestParam(defaultValue = "100") int count) {
        try {
            int successCount = orderService.batchCreateOrders(count);
            return ResponseDTO.success(
                    String.format("批量创建完成，成功：%d，失败：%d", successCount, count - successCount),
                    successCount
            );
        } catch (Exception e) {
            return ResponseDTO.error("批量创建失败：" + e.getMessage());
        }
    }

    /**
     * 快速批量插入订单数据（使用批量插入，性能更好）
     * POST /order/batch-insert
     */
    @PostMapping("/batch-insert")
    public ResponseDTO<Integer> batchInsertOrders(@RequestParam(defaultValue = "10000") int count) {
        try {
            Map<String, Object> result = orderService.batchGenerateOrders(count);
            if ((Boolean) result.get("success")) {
                return ResponseDTO.success(
                        (String) result.get("message"),
                        (Integer) result.get("data")
                );
            } else {
                return ResponseDTO.error((String) result.get("message"));
            }
        } catch (Exception e) {
            return ResponseDTO.error("批量插入失败：" + e.getMessage());
        }
    }

    // ========== RocketMQ 测试接口 ==========

    /**
     * 发送订单创建消息
     * POST /order/send-message
     */
    @PostMapping("/send-message")
    public ResponseDTO<String> sendOrderMessage(
            @RequestParam String orderCode,
            @RequestParam Long userId,
            @RequestParam BigDecimal totalAmount,
            @RequestParam String address,
            @RequestParam(defaultValue = "ORDER_CREATE") String messageType) {
        try {
            // 构建订单消息
            OrderMessage orderMessage = OrderMessage.builder()
                    .orderCode(orderCode)
                    .userId(userId)
                    .totalAmount(totalAmount)
                    .status(0)
                    .address(address)
                    .orderTime(new Date())
                    .messageType(messageType)
                    .build();

            // 发送消息
            boolean success = orderProducer.sendOrderMessage(orderMessage);

            if (success) {
                return ResponseDTO.success("订单消息发送成功", orderCode);
            } else {
                return ResponseDTO.error("订单消息发送失败");
            }
        } catch (Exception e) {
            return ResponseDTO.error("发送消息失败：" + e.getMessage());
        }
    }

    /**
     * 发送订单支付消息
     * POST /order/send-pay-message
     */
    @PostMapping("/send-pay-message")
    public ResponseDTO<String> sendPayMessage(
            @RequestParam String orderCode,
            @RequestParam Long userId,
            @RequestParam BigDecimal totalAmount) {
        try {
            // 构建订单支付消息
            OrderMessage orderMessage = OrderMessage.builder()
                    .orderCode(orderCode)
                    .userId(userId)
                    .totalAmount(totalAmount)
                    .status(1)
                    .orderTime(new Date())
                    .messageType("ORDER_PAY")
                    .build();

            // 发送消息
            boolean success = orderProducer.sendOrderMessage(orderMessage);

            if (success) {
                return ResponseDTO.success("订单支付消息发送成功", orderCode);
            } else {
                return ResponseDTO.error("订单支付消息发送失败");
            }
        } catch (Exception e) {
            return ResponseDTO.error("发送消息失败：" + e.getMessage());
        }
    }

    /**
     * 发送订单发货消息
     * POST /order/send-ship-message
     */
    @PostMapping("/send-ship-message")
    public ResponseDTO<String> sendShipMessage(
            @RequestParam String orderCode,
            @RequestParam Long userId,
            @RequestParam String address) {
        try {
            // 构建订单发货消息
            OrderMessage orderMessage = OrderMessage.builder()
                    .orderCode(orderCode)
                    .userId(userId)
                    .status(2)
                    .address(address)
                    .orderTime(new Date())
                    .messageType("ORDER_SHIP")
                    .build();

            // 发送消息
            boolean success = orderProducer.sendOrderMessage(orderMessage);

            if (success) {
                return ResponseDTO.success("订单发货消息发送成功", orderCode);
            } else {
                return ResponseDTO.error("订单发货消息发送失败");
            }
        } catch (Exception e) {
            return ResponseDTO.error("发送消息失败：" + e.getMessage());
        }
    }

    /**
     * 批量发送订单消息（用于压力测试）
     * POST /order/batch-send-message
     */
    @PostMapping("/batch-send-message")
    public ResponseDTO<Integer> batchSendOrderMessage(
            @RequestParam(defaultValue = "10") int count,
            @RequestParam(defaultValue = "ORDER_CREATE") String messageType) {
        try {
            int successCount = 0;
            for (int i = 0; i < count; i++) {
                String orderCode = "TEST" + System.currentTimeMillis() + i;
                Long userId = 1L + (long) (Math.random() * 100);
                BigDecimal totalAmount = new BigDecimal(100 + Math.random() * 900);
                String address = "测试地址" + i;

                OrderMessage orderMessage = OrderMessage.builder()
                        .orderCode(orderCode)
                        .userId(userId)
                        .totalAmount(totalAmount)
                        .status(0)
                        .address(address)
                        .orderTime(new Date())
                        .messageType(messageType)
                        .build();

                boolean success = orderProducer.sendOrderMessage(orderMessage);
                if (success) {
                    successCount++;
                }

                // 添加延迟，避免发送过快
                Thread.sleep(100);
            }

            return ResponseDTO.success(
                    String.format("批量发送完成，成功：%d，失败：%d", successCount, count - successCount),
                    successCount
            );
        } catch (Exception e) {
            return ResponseDTO.error("批量发送失败：" + e.getMessage());
        }
    }
}
