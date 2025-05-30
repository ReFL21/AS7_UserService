package com.example.User_Service.business.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.User_Service.domain.User;
import com.example.User_Service.repository.Role;
import com.example.User_Service.repository.UserEntity;
import com.example.User_Service.repository.UserRepository;
import com.example.User_Service.repository.UserRoleEntity;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {GetUserByIdImpl.class})
@DisabledInAotMode
@ExtendWith(SpringExtension.class)
class GetUserByIdImplDiffblueTest {
    @Autowired
    private GetUserByIdImpl getUserByIdImpl;

    @MockitoBean
    private UserRepository userRepository;

    @Test
    @DisplayName("Test getUserById(Long)")
    @Tag("MaintainedByDiffblue")
    void testGetUserById() {
        // Arrange
        UserRoleEntity userRoles = new UserRoleEntity();
        userRoles.setId(1L);
        userRoles.setRole(Role.Customer);
        userRoles.setUser(new UserEntity());

        UserEntity user = new UserEntity();
        user.setAddress("42 Main St");
        user.setCity("Oxford");
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setUserRoles(userRoles);
        user.setUsername("janedoe");

        UserRoleEntity userRoles2 = new UserRoleEntity();
        userRoles2.setId(1L);
        userRoles2.setRole(Role.Customer);
        userRoles2.setUser(user);

        UserEntity userEntity = new UserEntity();
        userEntity.setAddress("42 Main St");
        userEntity.setCity("Oxford");
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setId(1L);
        userEntity.setName("Name");
        userEntity.setPassword("iloveyou");
        userEntity.setUserRoles(userRoles2);
        userEntity.setUsername("janedoe");
        Optional<UserEntity> ofResult = Optional.of(userEntity);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        Optional<User> actualUserById = getUserByIdImpl.getUserById(1L);

        // Assert
        verify(userRepository).findById(eq(1L));
        User getResult = actualUserById.get();
        assertEquals("42 Main St", getResult.getAddress());
        assertEquals("Name", getResult.getName());
        assertEquals("Oxford", getResult.getCity());
        assertEquals("iloveyou", getResult.getPassword());
        assertEquals("jane.doe@example.org", getResult.getEmail());
        assertEquals("janedoe", getResult.getUsername());
        assertEquals(1L, getResult.getId());
        assertTrue(actualUserById.isPresent());
    }
}
