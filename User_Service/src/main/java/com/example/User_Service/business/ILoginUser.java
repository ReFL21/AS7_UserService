package com.example.User_Service.business;

import com.example.User_Service.domain.LoginRequest;
import com.example.User_Service.domain.LoginResponse;

public interface ILoginUser {
    LoginResponse login(LoginRequest request);
}
