package com.example.User_Service.business;

import com.example.User_Service.domain.User;

import java.util.Optional;

public interface IGetUserById {
     Optional<User> getUserById(Long id);
}
