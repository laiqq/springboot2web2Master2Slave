package com.jony.platform.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by jony
 */
@Component
@RabbitListener(queues = "order")
public class Receiver {
    @RabbitHandler
    public void process(String order){
        System.out.println("Receiver:"+order);
    }
}
