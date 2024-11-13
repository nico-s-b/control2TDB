package com.example.control2.services;

import com.example.control2.models.User;
import com.example.control2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public User getUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public void createUser(User user) {
        userRepository.saveUser(user);
    }

    public void removeUser(User user) {
        userRepository.deleteUser(user);
    }
}

