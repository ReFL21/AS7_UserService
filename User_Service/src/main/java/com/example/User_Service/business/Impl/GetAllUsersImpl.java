package com.example.User_Service.business.Impl;

import com.example.User_Service.business.IGetAllUsers;
import com.example.User_Service.business.UserConverter;
import com.example.User_Service.domain.GetAllUsersResponse;
import com.example.User_Service.domain.User;
import com.example.User_Service.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class GetAllUsersImpl implements IGetAllUsers {
    private UserRepository userRepository;

    @Override
    @Transactional
    public GetAllUsersResponse getAllUsers(){
        List<User> users;
        users = userRepository.findAll()
                .stream()
                .map(UserConverter::convert)
                .toList();

        return GetAllUsersResponse.builder()
                .users(users)
                .build();


    }
}
