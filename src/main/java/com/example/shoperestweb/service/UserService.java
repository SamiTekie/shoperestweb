package com.example.shoperestweb.service;

import com.example.shoperestweb.model.User;
import com.example.shoperestweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        // Implement update logic here, you can use userRepository.save(user) to update the user
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
