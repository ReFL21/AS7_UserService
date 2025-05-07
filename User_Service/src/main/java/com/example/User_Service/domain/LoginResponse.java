package com.example.User_Service.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private String accessToken;
    private String idToken;
    private String tokenType;
    private Long expiresIn;
}
