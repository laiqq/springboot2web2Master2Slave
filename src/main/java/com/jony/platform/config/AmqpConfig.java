package com.jony.platform.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by Jony 
 */
@Configuration
public class AmqpConfig {
    @Bean
    public Queue helloQueue() {
        return new Queue("order");
    }
}
