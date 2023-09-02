package com.example.shoperestweb.controller;

import com.example.shoperestweb.dto.UserDTO;
import com.example.shoperestweb.model.Role;
import com.example.shoperestweb.model.User;
import com.example.shoperestweb.service.RoleService;
import com.example.shoperestweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService; // Inject RoleService

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService; // Initialize RoleService
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Hash the password

        // Parse and assign roles from the request
        List<Role> roles = new ArrayList<>();
        for (String roleName : userDTO.getRoles()) {
            Role role = roleService.getRoleByName(roleName); // Use roleService to find roles
            if (role != null) {
                roles.add(role);
            }
        }
        user.setRoles(new HashSet<>(roles));

        //user.setRoles(roles);

        userService.createUser(user); // Use userService to create the user

        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
