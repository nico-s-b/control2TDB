package com.example.control2.repositories;

import com.example.control2.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    User findUserByEmail(String email);
    User findUserByUsername(String username);
    User findUserById(Long id);
    User saveUser(User user);
    void deleteUser(User user);
}
