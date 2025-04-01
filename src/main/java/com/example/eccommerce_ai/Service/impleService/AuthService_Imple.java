package com.example.eccommerce_ai.Service.impleService;

import com.example.eccommerce_ai.dto.AuthResponseDTO;
import com.example.eccommerce_ai.dto.LoginRequest;
import com.example.eccommerce_ai.dto.RegisterRequest;
import com.example.eccommerce_ai.dto.UserDTO;
import com.example.eccommerce_ai.respone.AuthResponse;

public interface AuthService_Imple {

    UserDTO registerUser(RegisterRequest request);
   // UserDTO registerAdmin(RegisterRequest request);
    void requestOtpForSignup(String email);
    void createAndSendResetLink(String email);
    AuthResponse authenticateUser(String email, String password);
    //   AuthResponseDTO login(LoginRequest request);
    //AuthResponseDTO refreshToken(String refreshToken);
}
