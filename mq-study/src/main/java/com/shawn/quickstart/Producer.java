package com.shawn.quickstart;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shawn
 * @date 2023/7/18 00:26
 * @description
 */
public class Producer {
    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("mq_producer_01");
        producer.setNamesrvAddr("47.98.59.193:9876");
        try {
            producer.start();
            int timeout = producer.getSendMsgTimeout();
            System.out.println("timeout = " + timeout);
            // 发送消息
            Message msg = new Message("TOPIC_TEST", "hello rocketmq".getBytes());
            SendResult sendResult = producer.send(msg);
            // 输出发送消息的结果
            System.out.println(sendResult);

            // 发送带key的消息
            msg = new Message("TOPIC_TEST", null, "ODS2023071800290001", "{\"id\":1, \"orderNo\":\"ODS2023071800290001\",\"buyerId\":1,\"sellerId\":1  }".getBytes());
            sendResult = producer.send(msg);
            System.out.println(sendResult);

            // 发送批量消息
            List<Message> msgs = new ArrayList<>();
            msgs.add(new Message("TOPIC_TEST", null, "ODS2023071800290002", "{\"id\":2, \"orderNo\":\"ODS2023071800290002\",\"buyerId\":1,\"sellerId\":3  }".getBytes()));
            msgs.add(new Message("TOPIC_TEST", null, "ODS2023071800290003", "{\"id\":3, \"orderNo\":\"ODS2023071800290003\",\"buyerId\":2,\"sellerId\":4  }".getBytes()));
            sendResult = producer.send(msgs);
            System.out.println(sendResult);
        } catch (MQClientException | MQBrokerException | RemotingException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            producer.shutdown();
        }

    }
}
