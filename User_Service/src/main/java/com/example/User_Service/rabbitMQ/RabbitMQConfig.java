package com.example.User_Service.rabbitMQ;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.*;
import org.springframework.context.annotation.*;

@Configuration
public class RabbitMQConfig {
    public static final String USER_EXCHANGE       = "user.exchange";
    public static final String USER_DELETE_QUEUE   = "user.delete.queue";
    public static final String USER_DELETE_ROUTING = "user.deleted";

    @Bean
    TopicExchange userExchange() {
        return new TopicExchange(USER_EXCHANGE);
    }
    @Bean
    Queue userDeleteQueue() {
        return QueueBuilder.durable(USER_DELETE_QUEUE).build();
    }
    @Bean
    Binding userDeleteBinding(Queue userDeleteQueue, TopicExchange userExchange) {
        return BindingBuilder
                .bind(userDeleteQueue)
                .to(userExchange)
                .with(USER_DELETE_ROUTING);
    }

    @Bean
    Jackson2JsonMessageConverter jsonConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate(
            ConnectionFactory cf,
            Jackson2JsonMessageConverter converter
    ) {
        RabbitTemplate tpl = new RabbitTemplate(cf);
        tpl.setMessageConverter(converter);
        return tpl;
    }


}
