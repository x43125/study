package com.zf.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 订单消息生产者
 */
@Slf4j
@Component
public class OrderProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 订单主题
     */
    private static final String ORDER_TOPIC = "order-topic";

    /**
     * 发送订单消息
     *
     * @param orderMessage 订单消息
     * @return 发送是否成功
     */
    public boolean sendOrderMessage(OrderMessage orderMessage) {
        try {
            // 设置消息发送时间
            orderMessage.setSendTime(new java.util.Date());

            // 构建消息
            Message<OrderMessage> message = MessageBuilder.withPayload(orderMessage).build();

            // 发送消息（同步发送）
            rocketMQTemplate.syncSend(ORDER_TOPIC, message);

            log.info("订单消息发送成功 - 订单编号: {}, 消息类型: {}, 发送时间: {}",
                    orderMessage.getOrderNo(),
                    orderMessage.getMessageType(),
                    orderMessage.getSendTime());

            return true;
        } catch (Exception e) {
            log.error("订单消息发送失败 - 订单编号: {}, 错误信息: {}",
                    orderMessage.getOrderNo(),
                    e.getMessage(),
                    e);
            return false;
        }
    }

    /**
     * 发送订单消息（异步发送）
     *
     * @param orderMessage 订单消息
     */
    public void sendOrderMessageAsync(OrderMessage orderMessage) {
        try {
            // 设置消息发送时间
            orderMessage.setSendTime(new java.util.Date());

            // 构建消息
            Message<OrderMessage> message = MessageBuilder.withPayload(orderMessage).build();

            // 异步发送消息
            rocketMQTemplate.asyncSend(ORDER_TOPIC, message, new org.apache.rocketmq.client.producer.SendCallback() {
                @Override
                public void onSuccess(org.apache.rocketmq.client.producer.SendResult sendResult) {
                    log.info("异步消息发送成功 - 订单编号: {}", orderMessage.getOrderNo());
                }

                @Override
                public void onException(Throwable e) {
                    log.error("异步消息发送失败 - 订单编号: {}, 错误信息: {}",
                            orderMessage.getOrderNo(), e.getMessage(), e);
                }
            });

            log.info("订单消息异步发送中 - 订单编号: {}", orderMessage.getOrderNo());
        } catch (Exception e) {
            log.error("订单消息异步发送失败 - 订单编号: {}, 错误信息: {}",
                    orderMessage.getOrderNo(),
                    e.getMessage(),
                    e);
        }
    }
}