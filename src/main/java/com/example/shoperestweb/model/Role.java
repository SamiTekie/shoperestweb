package com.example.shoperestweb.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "role") // This establishes the bidirectional relationship
    private User user;
}
