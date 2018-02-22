package com.jony.platform.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import com.jony.platform.model.bean.Order;


/**
 * Created by jony
 */
@Component
public class Sender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(int i) throws Exception {
    	Order order = new Order();
    	order.setOrderNo("20170824000001");
    	order.setPrint("452");
    	order.setUserId("laiqq");
    	order.setMsgId(String.valueOf(i));
        String context = "开始发生订单消息了";
        //用户组对象转JSON串  
        String jsonString = JSON.toJSONString(order);   
        System.out.println(context+"Sender:"+jsonString);
        // JSON串转用户组对象  
        //Order object = JSON.parseObject(jsonString, Order.class);  
 
        amqpTemplate.convertAndSend("order",jsonString);
    }
}
