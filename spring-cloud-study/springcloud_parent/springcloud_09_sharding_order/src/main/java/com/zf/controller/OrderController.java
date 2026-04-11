package com.zf.controller;

import com.zf.dto.CreateOrderDTO;
import com.zf.dto.ResponseDTO;
import com.zf.entity.Order;
import com.zf.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
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

    /**
     * 创建订单
     * POST /order/create
     */
    @PostMapping("/create")
    public ResponseDTO<Long> createOrder(@Valid @RequestBody CreateOrderDTO createOrderDTO) {
        Long orderId = orderService.createOrderWithDTO(createOrderDTO);
        return ResponseDTO.success("订单创建成功", orderId);
    }

    /**
     * 查询订单详情
     * GET /order/{id}
     */
    @GetMapping("/{id}")
    public ResponseDTO<Order> getOrder(@PathVariable Long id) {
        Order order = orderService.getOrderWithItems(id);
        if (order == null) {
            throw new IllegalArgumentException("订单不存在");
        }
        return ResponseDTO.success(order);
    }

    /**
     * 查询用户订单列表
     * GET /order/user/{userId}
     */
    @GetMapping("/user/{userId}")
    public ResponseDTO<List<Order>> getUserOrders(@PathVariable Long userId) {
        List<Order> orders = orderService.getUserOrders(userId);
        return ResponseDTO.success(orders);
    }

    /**
     * 更新订单状态
     * PUT /order/{id}/status
     */
    @PutMapping("/{id}/status")
    public ResponseDTO<Void> updateOrderStatus(@PathVariable Long id, @RequestParam Integer status) {
        boolean success = orderService.updateOrderStatus(id, status);
        if (!success) {
            throw new IllegalArgumentException("状态更新失败");
        }
        return ResponseDTO.success("状态更新成功");
    }

    /**
     * 统计订单数量
     * GET /order/count
     */
    @GetMapping("/count")
    public ResponseDTO<Long> countOrders() {
        long count = orderService.countOrders();
        return ResponseDTO.success(count);
    }

    /**
     * 批量生成订单数据（用于测试）
     * POST /order/batch
     */
    @PostMapping("/batch")
    public ResponseDTO<Integer> batchCreateOrders(@RequestParam(defaultValue = "100") int count) {
        int successCount = orderService.batchCreateOrders(count);
        return ResponseDTO.success(
                String.format("批量创建完成，成功：%d，失败：%d", successCount, count - successCount),
                successCount);
    }

    /**
     * 快速批量插入订单数据（使用批量插入，性能更好）
     * POST /order/batch-insert
     */
    @PostMapping("/batch-insert")
    public ResponseDTO<Integer> batchInsertOrders(@RequestParam(defaultValue = "10000") int count) {
        Map<String, Object> result = orderService.batchGenerateOrders(count);
        if (!(Boolean) result.get("success")) {
            throw new RuntimeException((String) result.get("message"));
        }
        return ResponseDTO.success(
                (String) result.get("message"),
                (Integer) result.get("data"));
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
            @RequestParam String address) {
        boolean success = orderService.sendOrderCreateMessage(orderCode, userId, totalAmount, address);
        if (!success) {
            throw new RuntimeException("订单消息发送失败");
        }
        return ResponseDTO.success("订单消息发送成功", orderCode);
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
        boolean success = orderService.sendOrderPayMessage(orderCode, userId, totalAmount);
        if (!success) {
            throw new RuntimeException("订单支付消息发送失败");
        }
        return ResponseDTO.success("订单支付消息发送成功", orderCode);
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
        boolean success = orderService.sendOrderShipMessage(orderCode, userId, address);
        if (!success) {
            throw new RuntimeException("订单发货消息发送失败");
        }
        return ResponseDTO.success("订单发货消息发送成功", orderCode);
    }

    /**
     * 批量发送订单消息（用于压力测试）
     * POST /order/batch-send-message
     */
    @PostMapping("/batch-send-message")
    public ResponseDTO<Integer> batchSendOrderMessage(
            @RequestParam(defaultValue = "10") int count,
            @RequestParam(defaultValue = "ORDER_CREATE") String messageType) {
        int successCount = orderService.batchSendOrderMessages(count, messageType);
        return ResponseDTO.success(
                String.format("批量发送完成，成功：%d，失败：%d", successCount, count - successCount),
                successCount);
    }
}