package com.example.User_Service.business.Impl;

import com.example.User_Service.business.ILoginUser;
import com.example.User_Service.domain.LoginRequest;
import com.example.User_Service.domain.LoginResponse;
import com.example.User_Service.repository.UserEntity;
import com.example.User_Service.repository.UserRepository;
import com.example.User_Service.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class LoginUserImpl implements ILoginUser {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private PasswordEncoder passwordEncoder;

    @Override
    public LoginResponse login(LoginRequest request) {

        Optional<UserEntity> userOptional = userRepository.findByUsername(request.getUsername());
        if (userOptional.isEmpty()) {
            throw new BadCredentialsException("Bad credentials");
        }
        UserEntity user = userOptional.get();

        if(!matchPassword(request.getPassword(), user.getPassword())){
            throw new BadCredentialsException("Bad credentials");
        }
        String roleName = user.getUserRoles().getRole().name();


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()
                )
        );
        String token = jwtUtil.generateToken(user.getUsername(), user.getId(), roleName);

        return LoginResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .expiresIn(jwtUtil.getExpirationMs())
                .build();
    }


    private boolean matchPassword(String rawPassword, String encodedPassword){
        if(passwordEncoder.matches(rawPassword, encodedPassword)){
            return true;
        }
        return false;
    }
}
