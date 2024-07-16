package com.bxc.order.service.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Bixc
 * @date: 2024/07/15 10:15
 **/
@Component
public class OrderlyProducer {

    private DefaultMQProducer Producer;

    public static final String NAMESERVER = "47.238.113.32:9876";

    public static final String Producer_GROUP_NAME = "orderly_producer_group_name";

    public OrderlyProducer() {
        this.Producer = new DefaultMQProducer(Producer_GROUP_NAME);
        this.Producer.setNamesrvAddr(NAMESERVER);
        this.Producer.setSendMsgTimeout(3000);
        start();
    }

    public void start(){
        try {
            this.Producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        this.Producer.shutdown();
    }

    public void sendOrderlyMessages(List<Message> messageList, int messageQueueNumber){
        for (Message message : messageList) {
            try {
                this.Producer.send(message, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                        Integer id = (Integer) o;
                        return list.get(id);
                    }
                },messageQueueNumber);
            } catch (MQClientException e) {
                throw new RuntimeException(e);
            } catch (RemotingException e) {
                throw new RuntimeException(e);
            } catch (MQBrokerException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }



}
