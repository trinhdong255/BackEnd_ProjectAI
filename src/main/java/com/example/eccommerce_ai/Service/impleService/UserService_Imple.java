package com.example.eccommerce_ai.Service.impleService;

import com.example.eccommerce_ai.dto.ChangePasswordRequest;
import com.example.eccommerce_ai.dto.RegisterRequest;
import com.example.eccommerce_ai.dto.UpdateUserRequest;
import com.example.eccommerce_ai.dto.UserDTO;
import com.example.eccommerce_ai.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService_Imple {
    UserDTO getUserProfile(Long userId);
    UserDTO updateUserProfile(Long userId, UpdateUserRequest updateUserRequest);
    void changePassword(Long userId, ChangePasswordRequest changePasswordRequest);
}
