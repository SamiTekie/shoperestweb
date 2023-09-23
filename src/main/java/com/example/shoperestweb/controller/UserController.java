package com.example.shoperestweb.controller;

import com.example.shoperestweb.dto.UserDTO;
import com.example.shoperestweb.service.UserService;
import com.example.shoperestweb.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO userDTO = userService.getUserById(id);
        if (userDTO != null) {
            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        UserDTO updatedUserDTO = userService.updateUser(id, userDTO);
        if (updatedUserDTO != null) {
            return ResponseEntity.ok(updatedUserDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
