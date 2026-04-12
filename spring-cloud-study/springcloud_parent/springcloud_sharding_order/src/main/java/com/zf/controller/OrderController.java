package com.zf.controller;

import com.zf.dto.CreateOrderDTO;
import com.zf.dto.ResponseDTO;
import com.zf.entity.Order;
import com.zf.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
        Long orderNo = orderService.createOrderWithDTO(createOrderDTO);
        return ResponseDTO.success("订单创建成功", orderNo);
    }

    /**
     * 查询订单详情
     * GET /order/{orderNo}
     */
    @GetMapping("/{orderNo}")
    public ResponseDTO<Order> getOrder(@PathVariable Long orderNo) {
        Order order = orderService.getOrderWithItems(orderNo);
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
     * PUT /order/{orderNo}/status
     */
    @PutMapping("/{orderNo}/status")
    public ResponseDTO<Void> updateOrderStatus(@PathVariable Long orderNo, @RequestParam Integer status) {
        boolean success = orderService.updateOrderStatus(orderNo, status);
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
}