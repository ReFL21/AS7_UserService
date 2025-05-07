package com.example.User_Service.business;

import com.example.User_Service.business.Impl.CreateUserImpl;
import com.example.User_Service.domain.CreateUserRequest;
import com.example.User_Service.domain.CreateUserResponse;
import com.example.User_Service.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateUserTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserRoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private CreateUserImpl createUserImpl;

    @Test
    void createUser_shouldCreateUserSuccessfully() {
        CreateUserRequest request = new CreateUserRequest("Hristo", "TestingUser", "h.kolev@test.com", "password", "City", "Address");

        when(userRepository.findByUsername("TestingUser")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(roleRepository.findByRole(Role.Customer)).thenReturn(Optional.of(new UserRoleEntity()));
        when(userRepository.save(any(UserEntity.class))).thenAnswer(i -> {
            UserEntity user = i.getArgument(0);
            user.setId(1L);
            return user;
        });
        CreateUserResponse response = createUserImpl.createUser(request);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        verify(userRepository).save(any(UserEntity.class));
    }

    @Test
    void createUser_shouldThrowExceptionIfUsernameAlreadyExists() {
        CreateUserRequest request = new CreateUserRequest("Ivan", "Username", "test@example.com", "password", "City", "Address");
        when(userRepository.findByUsername("Username")).thenReturn(Optional.of(new UserEntity()));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            createUserImpl.createUser(request);
        });

        assertEquals("Username 'Username' is already in use", exception.getMessage());
    }


}
