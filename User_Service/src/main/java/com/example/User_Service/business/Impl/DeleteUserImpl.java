package com.example.User_Service.business.Impl;

import com.example.User_Service.business.IDeleteUser;
import com.example.User_Service.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteUserImpl implements IDeleteUser {
    private final UserRepository userRepository;

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
