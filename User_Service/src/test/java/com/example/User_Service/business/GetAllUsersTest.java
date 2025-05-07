package com.example.User_Service.business;

import com.example.User_Service.business.Impl.GetAllUsersImpl;
import com.example.User_Service.domain.GetAllUsersResponse;
import com.example.User_Service.repository.UserEntity;
import com.example.User_Service.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllUsersTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private GetAllUsersImpl getAllUsersImpl;


    @Test
    void getAllUsers_shouldReturnListOfUsers() {
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .username("Ivan40")
                .email("Ivan40@abv.bg")
                .name("Ivan")

                .build();

        when(userRepository.findAll()).thenReturn(List.of(userEntity));

        GetAllUsersResponse response = getAllUsersImpl.getAllUsers();

        assertNotNull(response);
        assertEquals(1, response.getUsers().size());
        assertEquals("Ivan40", response.getUsers().get(0).getUsername());
        verify(userRepository, times(1)).findAll();
    }

}
