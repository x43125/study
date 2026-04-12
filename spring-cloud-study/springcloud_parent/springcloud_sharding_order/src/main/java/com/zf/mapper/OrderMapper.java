package com.zf.mapper;

import com.zf.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单Mapper
 */
@Mapper
public interface OrderMapper {

    /**
     * 插入订单
     */
    int insert(Order order);

    /**
     * 根据ID查询订单
     */
    Order selectById(@Param("id") Long id);

    /**
     * 根据用户ID查询订单列表
     */
    List<Order> selectByUserId(@Param("userId") Long userId);

    /**
     * 查询所有订单
     */
    List<Order> selectAll();

    /**
     * 统计订单数量
     */
    long selectCount();

    /**
     * 根据ID更新订单
     */
    int updateById(Order order);

    /**
     * 根据订单号更新订单
     */
    int updateByOrderNo(Order order);

    /**
     * 批量插入订单（用于快速生成测试数据）
     */
    void batchInsert(@Param("orders") List<Order> orders);
}
