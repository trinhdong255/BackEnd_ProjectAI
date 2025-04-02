package com.example.eccommerce_ai.controller;
 
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.RestController;
 import java.util.HashMap;
 import java.util.Map;
 
 @RestController
 public class HomeController {
 
     @GetMapping("/")
     public Map<String, Object> home() {
         Map<String, Object> response = new HashMap<>();
         response.put("message", "Welcome to E-CCommerce_AI API!");
         response.put("version", "1.0.0");
         response.put("status", "API is running");
 
         Map<String, String> endpoints = new HashMap<>();
         endpoints.put("register", "/api/auth/register (POST) - Register a new user");
         endpoints.put("login", "/api/auth/login (POST) - Login to get JWT token");
         endpoints.put("request-otp", "/api/auth/request-otp (POST) - Request a new OTP");
         endpoints.put("verify-otp", "/api/auth/verify-otp (POST) - Verify OTP to activate account");
         endpoints.put("forgot-password", "/api/auth/forgot-password (POST) - Request password reset link");
         endpoints.put("reset-password", "/api/auth/reset-password (POST) - Reset password");
 
         response.put("public-endpoints", endpoints);
         return response;
     }
 }
