package com.zf.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * 订单消息消费者
 * 监听订单主题的消息
 */
@Slf4j
@Component
@RocketMQMessageListener(
        topic = "order-topic",
        consumerGroup = "order-consumer-group",
        consumeMode = org.apache.rocketmq.spring.annotation.ConsumeMode.CONCURRENTLY,
        messageModel = org.apache.rocketmq.spring.annotation.MessageModel.CLUSTERING
)
public class OrderConsumer implements RocketMQListener<OrderMessage> {

    @Override
    public void onMessage(OrderMessage orderMessage) {
        try {
            log.info("========== 开始消费订单消息 ==========");
            log.info("订单编号: {}", orderMessage.getOrderCode());
            log.info("用户ID: {}", orderMessage.getUserId());
            log.info("订单金额: {}", orderMessage.getTotalAmount());
            log.info("订单状态: {}", orderMessage.getStatus());
            log.info("收货地址: {}", orderMessage.getAddress());
            log.info("下单时间: {}", orderMessage.getOrderTime());
            log.info("消息类型: {}", orderMessage.getMessageType());
            log.info("消息发送时间: {}", orderMessage.getSendTime());
            log.info("========== 订单消息消费完成 ==========");

            // 根据消息类型进行不同的业务处理
            handleMessageByType(orderMessage);

        } catch (Exception e) {
            log.error("消费订单消息失败 - 订单编号: {}, 错误信息: {}",
                    orderMessage.getOrderCode(),
                    e.getMessage(),
                    e);
            // 抛出异常让 RocketMQ 进行重试
            throw new RuntimeException("消费失败，触发重试", e);
        }
    }

    /**
     * 根据消息类型处理不同的业务逻辑
     *
     * @param orderMessage 订单消息
     */
    private void handleMessageByType(OrderMessage orderMessage) {
        String messageType = orderMessage.getMessageType();

        switch (messageType) {
            case "ORDER_CREATE":
                handleOrderCreate(orderMessage);
                break;
            case "ORDER_PAY":
                handleOrderPay(orderMessage);
                break;
            case "ORDER_SHIP":
                handleOrderShip(orderMessage);
                break;
            default:
                log.warn("未知的消息类型: {}", messageType);
        }
    }

    /**
     * 处理订单创建消息
     */
    private void handleOrderCreate(OrderMessage orderMessage) {
        log.info("处理订单创建消息 - 订单编号: {}", orderMessage.getOrderCode());
        // TODO: 这里可以添加订单创建后的业务逻辑
        // 例如：发送短信通知、更新库存、生成报表等
    }

    /**
     * 处理订单支付消息
     */
    private void handleOrderPay(OrderMessage orderMessage) {
        log.info("处理订单支付消息 - 订单编号: {}", orderMessage.getOrderCode());
        // TODO: 这里可以添加订单支付后的业务逻辑
        // 例如：更新订单状态、触发发货流程、发送支付成功通知等
    }

    /**
     * 处理订单发货消息
     */
    private void handleOrderShip(OrderMessage orderMessage) {
        log.info("处理订单发货消息 - 订单编号: {}", orderMessage.getOrderCode());
        // TODO: 这里可以添加订单发货后的业务逻辑
        // 例如：发送物流通知、更新订单状态等
    }
}