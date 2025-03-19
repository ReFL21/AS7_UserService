package com.example.User_Service.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <UserEntity, Long> {
  // List<UserEntity> findall();
}
