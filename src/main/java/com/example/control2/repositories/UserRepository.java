package com.example.control2.repositories;

import com.example.control2.models.User;

public interface UserRepository {
    User findUserByEmail(String email);
    User findUserByUsername(String username);
    User findUserById(Long id);
    void saveUser(User user);
    void deleteUser(User user);
}
