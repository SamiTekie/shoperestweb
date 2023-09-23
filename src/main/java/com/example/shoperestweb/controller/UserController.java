package com.example.shoperestweb.controller;

import com.example.shoperestweb.dto.UserDTO;
import com.example.shoperestweb.model.User;
import com.example.shoperestweb.service.UserService;
import com.example.shoperestweb.mapper.UserMapper; // Import the UserMapper

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper; // Inject the UserMapper

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper; // Initialize the UserMapper
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        Iterable<User> users = userService.getAllUsers();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(userMapper.toDTO(user)); // Map User to UserDTO
        }
        return userDTOs;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            UserDTO dto = userMapper.toDTO(user); // Convert User to UserDTO
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO); // Convert UserDTO to User
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash the password

        userService.createUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO); // Convert UserDTO to User

        User updatedUser = userService.updateUser(id, user);
        if (updatedUser != null) {
            UserDTO dto = userMapper.toDTO(updatedUser); // Convert User to UserDTO
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
