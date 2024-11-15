package com.example.control2.services;

import com.example.control2.models.Notifier;
import com.example.control2.models.User;
import com.example.control2.repositories.NotifierRepository;
import com.example.control2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotifierService notifierService;

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
        notifierService.create(user.getUserId());
    }

    public void removeUser(User user) {
        Notifier notifier = notifierService.findByUserId(user.getUserId());
        notifierService.delete(notifier);
        userRepository.deleteUser(user);
    }
}

