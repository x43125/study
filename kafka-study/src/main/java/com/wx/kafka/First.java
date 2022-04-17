package com.wx.kafka;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/12/07
 */
public class First {
    private KafkaProducer<String, String> producer;
    private KafkaConsumer<String, String> consumer;
    String topic = "demo-topic";

    public void initProducer() {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "47.98.59.193:9092");
        properties.setProperty(ProducerConfig.ACKS_CONFIG, "-1");
        properties.setProperty(ProducerConfig.RETRIES_CONFIG, "0");
        properties.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, "10");
        properties.setProperty(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, "10");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<>(properties);
    }

    public void initConsumer() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "47.98.59.193:9092");
        properties.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, 100 * 1024 * 1024);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "testGroupId");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        consumer = new KafkaConsumer<String, String>(properties);
    }

    public void producer() {
        String key = "key-demo";
        String value = "pipe-dream-x";

        for (int i = 0; i < 10; ++i) {
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, key + i, value + i);
            producer.send(record);
        }

        producer.close();
    }

    public void consumer() {
        consumer.subscribe(Arrays.asList(topic));

        while (true) {
//            consumer.poll(new Duration(10, 10));
            ConsumerRecords<String, String> records = consumer.poll(10);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record.topic() + "--" + record.partition() + "--" + record.value());
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        First first = new First();
        first.initConsumer();
        first.consumer();
        first.close();
    }

    public void close() {
        producer.close();
    }

}
