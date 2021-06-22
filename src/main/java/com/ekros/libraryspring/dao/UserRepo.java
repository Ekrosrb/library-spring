package com.ekros.libraryspring.dao;

import com.ekros.libraryspring.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> getUserByEmail(String email);
}