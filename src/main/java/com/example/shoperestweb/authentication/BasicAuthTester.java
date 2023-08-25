package com.example.shoperestweb.authentication;

import com.example.shoperestweb.model.User;
import com.example.shoperestweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component // Add this annotation to make it a Spring-managed component
public class BasicAuthTester {

    @Autowired
    private UserRepository userRepository; // Inject UserRepository directly

    public void testBasicAuth() {
        // Replace this URL with the endpoint you want to test
        String url = "http://localhost:8080/products/10";

        // Create RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        User user = userRepository.findByUsername("sami"); // Replace with the desired username
        String username = user.getUsername();
        String password = user.getPassword();

        // Create Basic Authentication header
        String credentials = username + ":" + password;
        String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + base64Credentials);

        // Create HTTP entity with headers
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            // Make a request with the Basic Authentication header
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            // Print the response
            System.out.println("Response: " + response.getBody());
        } catch (Exception e) {
            // Handle exceptions here, print an error message, or perform necessary actions
            System.err.println("Error: " + e.getMessage());
        }
    }
}
