package com.example.User_Service.business;

import com.example.User_Service.business.Impl.DeleteUserImpl;
import com.example.User_Service.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DeleteUserTests {
    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private DeleteUserImpl deleteUserMock;

    @Test
    void deleteUser_shouldDeleteUser(){
        deleteUserMock.deleteUser(1L);
        verify(userRepositoryMock).deleteById(1L);
    }

}
