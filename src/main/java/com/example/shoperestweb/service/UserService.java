package com.example.shoperestweb.service;

import com.example.shoperestweb.mapper.UserMapper;
import com.example.shoperestweb.model.User;
import com.example.shoperestweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper; // Inject the UserMapper

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper; // Initialize the UserMapper
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        user.setUserId(id);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
