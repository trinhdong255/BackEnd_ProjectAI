package com.example.eccommerce_ai.Service.impleService;

import com.example.eccommerce_ai.Enum.TokenType;

public interface EmailService_Imple {
    void sendOtp(String email, String otp );
    void sendPasswordResetEmail(String email, String resetLink);
    void  sendSignupSuccessEmail(String email);
}
