package com.example.shoperestweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.example.shoperestweb",
        "com.example.shoperestweb.service"
})
public class ShoperestwebApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShoperestwebApplication.class, args);
    }
}
