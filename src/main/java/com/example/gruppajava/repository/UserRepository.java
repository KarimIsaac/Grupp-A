package com.example.gruppajava.repository;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.example.gruppajava.entity.User;

public interface UserRepository extends ListCrudRepository<User, Long> {
  Optional<User> findByEmail(String email);
}
