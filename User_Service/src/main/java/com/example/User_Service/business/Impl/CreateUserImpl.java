package com.example.User_Service.business.Impl;

import com.example.User_Service.business.ICreateUser;
import com.example.User_Service.domain.CreateUserRequest;
import com.example.User_Service.domain.CreateUserResponse;
import com.example.User_Service.repository.Role;
import com.example.User_Service.repository.UserEntity;
import com.example.User_Service.repository.UserRepository;
import com.example.User_Service.repository.UserRoleEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
@AllArgsConstructor
public class CreateUserImpl implements ICreateUser {
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public CreateUserResponse createUser(CreateUserRequest userRequest) {

        UserEntity userEntity = saveNewUser(userRequest);
        return CreateUserResponse.builder()
                .id(userEntity.getId())
                .build();

    }

    private UserEntity saveNewUser(CreateUserRequest userRequest){
        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
        UserEntity user = UserEntity.builder()
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .name(userRequest.getName())
                .password(encodedPassword)
                .build();


        user.setUserRoles(Set.of(
                UserRoleEntity.builder()
                        .role(Role.Customer)
                        .user(user)
                        .build()
        ));

        return userRepository.save(user);
    }
}
