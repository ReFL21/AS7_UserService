package com.example.User_Service.business.Impl;

import com.example.User_Service.business.IGetUserById;
import com.example.User_Service.business.UserConverter;
import com.example.User_Service.domain.User;
import com.example.User_Service.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class GetUserByIdImpl implements IGetUserById {

    private final UserRepository userRepository;
    @Override
    @Transactional
    public Optional<User> getUserById(Long id){
         return userRepository.findById(id).map(UserConverter::convert);
    }

}
