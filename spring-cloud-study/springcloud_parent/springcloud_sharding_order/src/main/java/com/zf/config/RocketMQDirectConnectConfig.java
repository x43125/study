package com.zf.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.spring.autoconfigure.RocketMQProperties;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * RocketMQ 配置
 */
@Slf4j
@Configuration
@ConditionalOnClass(RocketMQTemplate.class)
public class RocketMQDirectConnectConfig {

    @Autowired
    private RocketMQProperties rocketMQProperties;

    @Bean
    public DefaultMQProducer customMQProducer() {
        DefaultMQProducer producer = new DefaultMQProducer();
        
        producer.setProducerGroup(rocketMQProperties.getProducer().getGroup());
        producer.setNamesrvAddr(rocketMQProperties.getNameServer());
        producer.setSendMsgTimeout(rocketMQProperties.getProducer().getSendMessageTimeout());
        producer.setRetryTimesWhenSendFailed(rocketMQProperties.getProducer().getRetryTimesWhenSendFailed());
        producer.setRetryTimesWhenSendAsyncFailed(rocketMQProperties.getProducer().getRetryTimesWhenSendAsyncFailed());
        
        log.info("RocketMQ Producer 已配置 - namesrvAddr: {}", producer.getNamesrvAddr());
        
        return producer;
    }

    @Bean
    @Primary
    public RocketMQTemplate rocketMQTemplate() {
        RocketMQTemplate template = new RocketMQTemplate();
        template.setProducer(customMQProducer());
        return template;
    }
}
