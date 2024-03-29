package com.example.shoperestweb.repository;

import com.example.shoperestweb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.role WHERE u.username = :username")
    User findByUsernameWithRole(@Param("username") String username);
}
