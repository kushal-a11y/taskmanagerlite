package com.taskmanagerlite.taskmanagerlite.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanagerlite.taskmanagerlite.auth.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
