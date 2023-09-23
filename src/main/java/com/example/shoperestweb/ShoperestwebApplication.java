package com.example.shoperestweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan; // Import ComponentScan annotation

@SpringBootApplication
@ComponentScan(basePackages = "com.example.shoperestweb.mapper") // Specify the base package for component scanning
public class ShoperestwebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoperestwebApplication.class, args);
    }
}
