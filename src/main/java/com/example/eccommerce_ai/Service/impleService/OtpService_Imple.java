package com.example.eccommerce_ai.Service.impleService;

import com.example.eccommerce_ai.Enum.TokenType;

public interface OtpService_Imple {
    void saveOTPRedis(String email, TokenType tokenType);
    String getOtp(String email, TokenType tokenType);
    boolean verifyOtp(String email, String userOtp, TokenType tokenType);

   void requestNewOtp (String email, TokenType tokenType);
}
