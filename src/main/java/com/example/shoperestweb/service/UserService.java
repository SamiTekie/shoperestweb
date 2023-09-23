package com.example.shoperestweb.service;

import com.example.shoperestweb.dto.UserDTO;
import com.example.shoperestweb.mapper.UserMapper;
import com.example.shoperestweb.model.User;
import com.example.shoperestweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.map(userMapper::toDTO).orElse(null);
    }

    public void createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        userRepository.save(user);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        Optional<User> existingUserOptional = userRepository.findById(id);
        return existingUserOptional.map(existingUser -> {
            User updatedUser = userMapper.toEntity(userDTO);
            updatedUser.setUserId(id);
            User savedUser = userRepository.save(updatedUser);
            return userMapper.toDTO(savedUser);
        }).orElse(null);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
