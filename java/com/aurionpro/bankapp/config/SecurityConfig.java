package com.aurionpro.bankapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for REST APIs
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/v1/auth/register").permitAll()
                .requestMatchers("/api/v1/auth/login").permitAll()
                .requestMatchers("/api/v1/auth/admin/login").permitAll()
                .requestMatchers("/api/v1/auth/customers/{customerId}/accounts").permitAll()
                .requestMatchers("/api/v1/auth/admin/accounts/{accountId}/status").permitAll()
                .requestMatchers("/api/v1/auth/customers/{customerId}/beneficiaries").permitAll()
                .requestMatchers("/api/v1/auth/customers/{customerId}/transactions").permitAll()
                .requestMatchers("/api/v1/auth/customers/{customerId}/passbook").permitAll()
                .requestMatchers("/api/v1/auth/customers/{customerId}/profile").permitAll()
                .requestMatchers("/api/v1/auth/admin/customers").permitAll()
                .requestMatchers("/api/v1/auth/admin/accounts/status/{status}").permitAll()
                .requestMatchers("/api/v1/auth/admin/transactions/search").permitAll()
                
                .anyRequest().authenticated()
            );
        return http.build();
    }
    
    
}
