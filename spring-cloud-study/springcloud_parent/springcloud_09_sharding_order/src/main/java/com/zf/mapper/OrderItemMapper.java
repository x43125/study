package com.zf.mapper;

import com.zf.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单明细Mapper
 */
@Mapper
public interface OrderItemMapper {

    /**
     * 插入订单明细
     */
    int insert(OrderItem item);

    /**
     * 根据ID查询订单明细
     */
    OrderItem selectById(@Param("id") Long id);

    /**
     * 根据订单ID查询订单明细列表
     */
    List<OrderItem> selectByOrderId(@Param("orderId") Long orderId);

    /**
     * 查询所有订单明细
     */
    List<OrderItem> selectAll();

    /**
     * 统计订单明细数量
     */
    long selectCount();

    /**
     * 根据ID更新订单明细
     */
    int updateById(OrderItem item);

    /**
     * 批量插入订单明细（用于快速生成测试数据）
     */
    void batchInsert(@Param("items") List<OrderItem> items);
}
