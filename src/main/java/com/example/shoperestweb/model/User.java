package com.example.shoperestweb.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToOne(cascade = CascadeType.PERSIST) // Cascade the persist operation to Role
    @JoinColumn(name = "role_id", unique = true) // Ensure a one-to-one relationship
    private Role role;


}
