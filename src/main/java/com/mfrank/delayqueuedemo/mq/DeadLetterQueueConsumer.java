package com.mfrank.delayqueuedemo.mq;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

import static com.mfrank.delayqueuedemo.config.DelayedRabbitMQConfig.DELAYED_QUEUE_NAME;
import static com.mfrank.delayqueuedemo.config.RabbitMQConfig.*;

@Slf4j
@Component
public class DeadLetterQueueConsumer {

    @RabbitListener(queues = DEAD_LETTER_QUEUEA_NAME)
    public void receiveA(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        log.info("当前时间：{},死信队列A收到消息：{}", new Date().toString(), msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = DEAD_LETTER_QUEUEB_NAME)
    public void receiveB(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        log.info("当前时间：{},死信队列B收到消息：{}", new Date().toString(), msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = DEAD_LETTER_QUEUEC_NAME)
    public void receiveC(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        log.info("当前时间：{},死信队列C收到消息：{}", new Date().toString(), msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = DELAYED_QUEUE_NAME)
    public void receiveD(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        log.info("当前时间：{},延时队列收到消息：{}", new Date().toString(), msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
