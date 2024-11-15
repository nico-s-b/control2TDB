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
    @Qualifier("notifierRepository")
    @Autowired
    private NotifierRepository notifierRepository;

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
        User newuser = userRepository.saveUser(user);

        Notifier notifier = new Notifier();
        notifier.setUserId(newuser.getUserId());
        notifier.setEnabled(false);
        notifier.setAmount(0);
        notifier.setTimeunit("day");

        notifierRepository.save(notifier);
    }

    public void removeUser(User user) {
        Notifier notifier = notifierRepository.findByUser(user.getUserId());
        notifierRepository.delete(notifier);
        userRepository.deleteUser(user);
    }
}

