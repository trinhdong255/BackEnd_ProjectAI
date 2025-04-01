package com.example.eccommerce_ai.contronler;

import com.example.eccommerce_ai.Enum.TokenType;
import com.example.eccommerce_ai.Service.*;
import com.example.eccommerce_ai.dto.*;
import com.example.eccommerce_ai.exception.BadRequestException;
import com.example.eccommerce_ai.exception.InvalidOtpException;
import com.example.eccommerce_ai.model.RefreshToken;
import com.example.eccommerce_ai.model.User;
import com.example.eccommerce_ai.repository.UserRepository;
import com.example.eccommerce_ai.respone.AuthResponse;
import com.example.eccommerce_ai.security.JwtProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final PasswordService passwordService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // ✅ 1. Đăng ký tài khoản (Lưu user & gửi OTP)
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody RegisterRequest request) {
        UserDTO newUser = authService.registerUser(request);
        return ResponseEntity.ok(newUser);
    }

    // ✅ 2. Yêu cầu mã OTP mới nếu OTP hết hạn
    @PostMapping("/request-otp")
    public ResponseEntity<String> requestNewOtp(@RequestParam String email) {
        authService.requestOtpForSignup(email);
        return ResponseEntity.ok("Mã OTP mới đã được gửi đến email của bạn!");
    }

    // ✅ 3. Xác thực OTP để kích hoạt tài khoản
    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        boolean isVerified = authService.verifySignupOtp(email, otp);
        if (!isVerified) {
            throw new InvalidOtpException("Mã OTP không hợp lệ hoặc đã hết hạn!");
        }
        return ResponseEntity.ok("Tài khoản đã được kích hoạt thành công!");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword()));
    }


    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email) {
        authService.createAndSendResetLink(email);
        return ResponseEntity.ok("Email đặt lại mật khẩu đã được gửi!");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String email, @RequestParam String token, @RequestParam String newPassword) {
        return authService.resetPassword(email, token, newPassword);
    }

/*
 @PostMapping("/register-admin")
    public ResponseEntity<?> registerAdmin(@RequestBody RegisterRequest request) {
        try {
            authService.registerAdmin(request);
            return ResponseEntity.ok("Admin đăng ký thành công!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lỗi đăng ký: " + e.getMessage());
        }
    }

 */


 /*

    // ✅ ĐĂNG NHẬP (LOGIN)
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = (User) authentication.getPrincipal();

        // 🔹 Tạo Access Token (Hết hạn trong 1 giờ)
        String accessToken = jwtProvider.generateToken(user, 3600);

        // 🔹 Tạo Refresh Token (Hết hạn trong 7 ngày)
        RefreshToken refreshToken = tokenService.createRefreshToken(user);

        // 🔹 Trả về thông tin user
        AuthResponse response = AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .userId(user.getId())
                .email(user.getEmail())
                .build();

        return ResponseEntity.ok(response);
    }
    // ✅ 2. ĐĂNG XUẤT (LOGOUT)
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody TokenRefreshRequestDTO request) {
        tokenService.deleteToken(request.getRefreshToken());
        return ResponseEntity.ok("✅ Đăng xuất thành công!");
    }

  */
}
