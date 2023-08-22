package com.example.shoperestweb.authentication;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class BasicAuthTester {
    public static void main(String[] args) {

        String username = "sami";
        String password = "tekie";

        // Replace this URL with the endpoint you want to test
        String url = "http://localhost:8080/products/10";

        //String url = "http://localhost:8080/users/1"; // Replace with the actual endpoint URL

       // String url = "http://localhost:8080/products"; // Replace with your server URL
       // String url = "http://localhost:8080/login"; // Replace with the actual endpoint URL



        // Create RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Create Basic Authentication header
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);

        // Create HTTP entity with headers
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Make a request with the Basic Authentication header
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        // Print the response
        System.out.println("Response: " + response.getBody());
    }
}

