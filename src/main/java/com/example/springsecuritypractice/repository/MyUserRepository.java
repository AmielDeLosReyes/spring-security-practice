package com.example.springsecuritypractice.repository;

import com.example.springsecuritypractice.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyUserRepository extends JpaRepository<MyUser, Long>{

    Optional<MyUser> findByUsername(String username);
}
