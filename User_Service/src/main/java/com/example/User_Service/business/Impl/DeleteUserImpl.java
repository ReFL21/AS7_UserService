package com.example.User_Service.business.Impl;

import com.example.User_Service.business.IDeleteUser;
import com.example.User_Service.rabbitMQ.RabbitMQConfig;
import com.example.User_Service.rabbitMQ.UserDeleteEvent;
import com.example.User_Service.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteUserImpl implements IDeleteUser {
    private final UserRepository userRepository;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void deleteUser(Long id) {

        userRepository.deleteById(id);
        UserDeleteEvent event = UserDeleteEvent.builder()
                .userId(id)
                .build();
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.USER_EXCHANGE,
                RabbitMQConfig.USER_DELETE_ROUTING,
                event
        );

    }
}
