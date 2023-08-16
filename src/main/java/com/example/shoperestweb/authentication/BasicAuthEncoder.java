package com.example.shoperestweb.authentication;

import java.util.Base64;

public class BasicAuthEncoder {
    public static void main(String[] args) {

        String username = "sami";
        String password = "tekie";

        // Combine username and password with a colon
        String credentials = username + ":" + password;

        // Encode the credentials to base64
        String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        // Print the encoded value
        System.out.println("Encoded value: " + base64Credentials);
    }
}
