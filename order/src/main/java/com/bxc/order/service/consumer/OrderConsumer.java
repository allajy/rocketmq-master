package com.bxc.order.service.consumer;

import com.bxc.order.constants.OrderStatus;
import com.bxc.order.mapper.OrderMapper;
import com.bxc.order.service.OrderService;
import com.bxc.order.utils.FastJsonConvertUtil;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: Bixc
 * @date: 2024/07/15 10:17
 **/
@Component
public class OrderConsumer {

    private DefaultMQPushConsumer consumer;

    public static final String CALLBACK_PAY_TOPIC = "callback_pay_topic";

    public static final String CALLBACK_PAY_TAGS = "callback_pay";

    public static final String NAMESERVER = "47.238.113.32:9876";

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderService orderService;

    public OrderConsumer() throws MQClientException{
        consumer = new DefaultMQPushConsumer("callback_pay_consumer_group");
        consumer.setConsumeThreadMin(10);
        consumer.setConsumeThreadMax(50);
        consumer.setNamesrvAddr(NAMESERVER);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.subscribe(CALLBACK_PAY_TOPIC,CALLBACK_PAY_TAGS);
        consumer.registerMessageListener(new MessageListenerConcurrently4Pay());
        consumer.start();
    }

    class MessageListenerConcurrently4Pay implements MessageListenerConcurrently {

        @Override
        public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
            MessageExt msg = list.get(0);
            try{
                String topic = msg.getTopic();
                String msgBody = new String(msg.getBody(),"utf-8");
                String tags = msg.getTags();
                String keys = msg.getKeys();
                System.out.println("收到消息：" + "  topic :" + topic + "  ,tags : " + tags + "keys :" + keys + ", msg : " + msgBody);
                String orignMsgId = msg.getProperties().get(MessageConst.PROPERTY_ORIGIN_MESSAGE_ID);
                System.out.println("orignMsgId: " + orignMsgId);

                //通过keys 进行去重表去重 或者使用redis进行去重？？？？ --》不需要
                Map<String,Object> body = FastJsonConvertUtil.convertJSONToObject(msgBody,Map.class);
                String orderId = (String) body.get("orderId");
                String userId = (String) body.get("userId");
                String status = (String)body.get("status");

                Date currentTime = new Date();

                if(status.equals(OrderStatus.ORDER_PAYED.getValue())){
                    int count  = orderMapper.updateOrderStatus(orderId, status, "admin", currentTime);
                    if(count == 1) {
                        orderService.sendOrderlyMessage4Pkg(userId, orderId);
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
    }

}
