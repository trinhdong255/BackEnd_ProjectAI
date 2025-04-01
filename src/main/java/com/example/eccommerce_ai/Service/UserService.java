package com.example.eccommerce_ai.Service;

import com.example.eccommerce_ai.Service.impleService.UserService_Imple;
import com.example.eccommerce_ai.dto.ChangePasswordRequest;
import com.example.eccommerce_ai.dto.RegisterRequest;
import com.example.eccommerce_ai.dto.UpdateUserRequest;
import com.example.eccommerce_ai.dto.UserDTO;
import com.example.eccommerce_ai.exception.AlreadyExistsException;
import com.example.eccommerce_ai.exception.UserNotFoundException;
import com.example.eccommerce_ai.model.User;
import com.example.eccommerce_ai.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    /*

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO getUserProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User không tồn tại"));
        return new UserDTO(user.getId(),
                           user.getUsername(),
                            user.getEmail(),
                           user.isActivated(),
                           user.getCreatedAt());
    }

    @Override
    public UserDTO updateUserProfile(Long userId, UpdateUserRequest updateUserRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User không tồn tại"));

        user.setUsername(updateUserRequest.getUsername());
        userRepository.save(user);

        return new UserDTO(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.isActivated(),
                user.getCreatedAt());
    }

    @Override
    public void changePassword(Long userId, ChangePasswordRequest changePasswordRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User không tồn tại"));

        if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())) {
            throw new BadCredentialsException("Mật khẩu cũ không đúng");
        }

        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        userRepository.save(user);
    }
     */


}


