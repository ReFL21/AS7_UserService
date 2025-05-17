package com.example.User_Service.business.Impl;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import com.example.User_Service.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DeleteUserImpl.class, RabbitTemplate.class})
@DisabledInAotMode
@ExtendWith(SpringExtension.class)
class DeleteUserImplDiffblueTest {
    @Autowired
    private DeleteUserImpl deleteUserImpl;

    @MockitoBean
    private RabbitTemplate rabbitTemplate;

    @MockitoBean
    private UserRepository userRepository;

    /**
     * Test {@link DeleteUserImpl#deleteUser(Long)}.
     * <ul>
     *   <li>Given {@link UserRepository} {@link CrudRepository#deleteById(Object)} does nothing.</li>
     *   <li>Then calls {@link CrudRepository#deleteById(Object)}.</li>
     * </ul>
     * <p>
     * Method under test: {@link DeleteUserImpl#deleteUser(Long)}
     */
    @Test
    @DisplayName("Test deleteUser(Long); given UserRepository deleteById(Object) does nothing; then calls deleteById(Object)")
    @Tag("MaintainedByDiffblue")
    void testDeleteUser_givenUserRepositoryDeleteByIdDoesNothing_thenCallsDeleteById() {
        // Arrange
        doNothing().when(userRepository).deleteById(Mockito.<Long>any());

        // Act
        deleteUserImpl.deleteUser(1L);

        // Assert
        verify(userRepository).deleteById(eq(1L));
    }
}
