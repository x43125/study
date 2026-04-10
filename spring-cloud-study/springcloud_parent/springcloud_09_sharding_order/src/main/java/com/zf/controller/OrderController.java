package com.zf.controller;

import com.zf.dto.CreateOrderDTO;
import com.zf.dto.ResponseDTO;
import com.zf.entity.Order;
import com.zf.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
}
