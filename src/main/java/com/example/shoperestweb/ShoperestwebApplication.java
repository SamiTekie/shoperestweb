package com.example.shoperestweb;

import com.example.shoperestweb.authentication.BasicAuthTester;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShoperestwebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoperestwebApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(BasicAuthTester basicAuthTester) {
        return args -> {
            // Execute the authentication testing logic
            basicAuthTester.testBasicAuth();
        };
    }
}
