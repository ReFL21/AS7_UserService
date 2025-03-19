package com.example.User_Service.business;

import com.example.User_Service.domain.CreateUserRequest;
import com.example.User_Service.domain.CreateUserResponse;

public interface ICreateUser {
    CreateUserResponse createUser(CreateUserRequest userRequest);
}
