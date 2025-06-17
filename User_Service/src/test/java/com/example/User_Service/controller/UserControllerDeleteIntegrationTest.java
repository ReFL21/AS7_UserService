// src/test/java/com/example/User_Service/controller/UserControllerDeleteIntegrationTest.java

package com.example.User_Service.controller;

import com.example.User_Service.UserServiceApplication;
import com.example.User_Service.rabbitMQ.RabbitMQConfig;
import com.example.User_Service.rabbitMQ.UserDeleteEvent;
import com.example.User_Service.repository.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        classes = UserServiceApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "spring.jpa.hibernate.ddl-auto=create-drop",
                "spring.jpa.show-sql=true"
        }
)
@AutoConfigureMockMvc(addFilters = false)  // disable security
@AutoConfigureTestDatabase               // use embedded H2
@Transactional                            // each test in its own tx
public class UserControllerDeleteIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @MockitoBean
    RabbitTemplate rabbitTemplate;  // only this is mocked

    @Test
    @DisplayName("DELETE /users/{id} → 204 + deletes user + publishes UserDeleteEvent")
    void shouldDeleteUserAndPublishEvent() throws Exception {
        // 1) Persist a RoleEntity
        UserRoleEntity persistedUserRole = userRoleRepository.save(
                UserRoleEntity.builder()
                        .role(Role.Customer)
                        .build()
        );

        // 2) Persist a UserEntity
        UserEntity user = userRepository.save(
                UserEntity.builder()
                        .username("tempUser")
                        .name("Temporary User")
                        .email("temp@example.com")
                        .password("secret")
                        .address("123 Test Street")
                        .city("Amsterdam")
                        .userRoles(
                                // create+persist the join
                                userRoleRepository.save(persistedUserRole)
                        )
                        .build()
        );

        // 3) Exercise the DELETE endpoint
        mockMvc.perform(delete("/users/{id}", user.getId()))
                .andExpect(status().isNoContent());

        // 4) Assert the user really is gone
        assertThat(userRepository.findById(user.getId())).isEmpty();

        // 5) And the RabbitMQ event was sent
        ArgumentCaptor<UserDeleteEvent> captor =
                ArgumentCaptor.forClass(UserDeleteEvent.class);
        verify(rabbitTemplate).convertAndSend(
                eq(RabbitMQConfig.USER_EXCHANGE),
                eq(RabbitMQConfig.USER_DELETE_ROUTING),
                captor.capture()
        );
        assertThat(captor.getValue().getUserId()).isEqualTo(user.getId());
    }
}
