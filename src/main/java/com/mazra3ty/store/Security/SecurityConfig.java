//package com.mazra3ty.store.Security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // تعطيل الـ CSRF عشان الـ Post يشتغل
//                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().permitAll() // السماح لكل الـ Requests بدون يوزر وباسورد
//                );
//        return http.build();
//    }
//}
