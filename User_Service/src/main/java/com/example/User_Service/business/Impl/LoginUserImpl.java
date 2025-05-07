package com.example.User_Service.business.Impl;

import com.example.User_Service.business.ILoginUser;
import com.example.User_Service.domain.LoginRequest;
import com.example.User_Service.domain.LoginResponse;
import com.example.User_Service.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class LoginUserImpl implements ILoginUser {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()
                )
        );
        String token = jwtUtil.generateToken(request.getUsername());
        return LoginResponse.builder()
                .accessToken(token)
                .idToken("")
                .tokenType("Bearer")
                .expiresIn(jwtUtil.getExpirationMs())
                .build();
    }
}
