package com.example.User_Service.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    // List<UserEntity> findall();
}
