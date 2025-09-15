package com.aurionpro.bankapp.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHasher {

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String plainPassword = "Pratham123";
        String hashedPassword = passwordEncoder.encode(plainPassword);
        
        System.out.println("Hashed Password for 'Admin123': " + hashedPassword);
    }
}
