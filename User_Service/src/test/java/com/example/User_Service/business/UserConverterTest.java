package com.example.User_Service.business;

import com.example.User_Service.domain.User;
import com.example.User_Service.repository.UserEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserConverterTest {
    @Test
    void ShouldConvertUserEntityToUser(){
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .username("Ivan40")
                .email("Ivan40@abv.bg")
                .name("Ivan")
                .address("Hemelrijken93")
                .city("Eindhoven")
                .build();

        User actualUser = UserConverter.convert(userEntity);

        User expectedUser = User.builder()
                .id(1L)
                .username("Ivan40")
                .email("Ivan40@abv.bg")
                .name("Ivan")
                .address("Hemelrijken93")
                .city("Eindhoven")
                .build();

        assertEquals(expectedUser, actualUser);
    }
}
