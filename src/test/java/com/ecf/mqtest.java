package com.ecf;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.remoting.protocol.heartbeat.MessageModel;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

@Slf4j
public class mqtest {
    @Test
    void contextLoads() throws MQClientException, MQBrokerException, RemotingException, InterruptedException, MQBrokerException, RemotingException, MQClientException {
        // 创建一个生产者(定制一个生产者组名)
        DefaultMQProducer producer = new DefaultMQProducer("test-producer-test");
        // 连接nameServer
        producer.setNamesrvAddr("svc.junhqin.com:9877");

        // 启动
        producer.start();

        // 创建一个消息
        Message message = new Message("topic1", "test哈哈1".getBytes());
        message.setKeys("1234");
        // 发送消息
        producer.setSendMsgTimeout(5000);
        SendResult send = producer.send(message);
        System.out.println(send.getSendStatus());

        // 关闭生产者
        producer.shutdown();

    }
    @Test
    void simpleConsumer() throws MQClientException, IOException {
        // 创建一个消费者
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test-consumer-group");
        // 连接namesrv
        consumer.setNamesrvAddr("svc.junhqin.com:9877");
        // 订阅一个主题 * 表示订阅这个主题中的所有消息，后期会有消息过滤
        consumer.subscribe("topic1","*");
        consumer.setMessageModel(MessageModel.BROADCASTING);
        consumer.setPullInterval(1000);  // 每隔 1 秒拉取一次消息
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        // 设置一个监听器
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            for (MessageExt msg : msgs) {
                System.out.println("接收到消息: " + new String(msg.getBody()));
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        // 启动
        consumer.start();
        // 挂起当前jvm,就是让其不要停止，因为消费逻辑是异步执行的，这里是为了防止主线程执行完而异步线程还没执行完
        System.in.read();

    }

}
