package com.example.User_Service.business;

import com.example.User_Service.domain.User;
import com.example.User_Service.repository.UserEntity;

public class UserConverter {
    public UserConverter(){}
    public static User convert(UserEntity userEntity){
        return User.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .name(userEntity.getName())
                .address(userEntity.getAddress())
                .city(userEntity.getCity())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .build();
    }
}
