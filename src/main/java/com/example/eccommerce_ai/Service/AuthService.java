package com.example.eccommerce_ai.Service;

import com.example.eccommerce_ai.Enum.TokenType;
import com.example.eccommerce_ai.Service.impleService.AuthService_Imple;
import com.example.eccommerce_ai.dto.AuthResponseDTO;
import com.example.eccommerce_ai.dto.LoginRequest;
import com.example.eccommerce_ai.dto.RegisterRequest;
import com.example.eccommerce_ai.dto.UserDTO;
import com.example.eccommerce_ai.exception.AlreadyExistsException;
import com.example.eccommerce_ai.exception.InvalidOtpException;
import com.example.eccommerce_ai.exception.InvalidTokenException;
import com.example.eccommerce_ai.exception.UserNotFoundException;
import com.example.eccommerce_ai.model.RefreshToken;
import com.example.eccommerce_ai.model.Role;

import com.example.eccommerce_ai.model.User;
import com.example.eccommerce_ai.repository.RoleRepository;
import com.example.eccommerce_ai.repository.UserRepository;
import com.example.eccommerce_ai.respone.AuthResponse;
import com.example.eccommerce_ai.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthService  implements AuthService_Imple{



    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final OtpService otpService;
    private final RedisService redisService;
    private final EmailService emailService;
    private final JwtProvider jwtService;
    private final TokenService tokenService;
    private final PasswordService passwordService;


    // ✅ Đăng ký tài khoản
    @Override
    public UserDTO registerUser(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AlreadyExistsException("Email đã tồn tại!");
        }

        // tim role

        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy role!"));

        // Tạo user mới
        User newUser = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .activated(false) // Chưa kích hoạt
                .createdAt(LocalDateTime.now())
                .build();
        userRepository.save(newUser);

        // Lưu OTP vào Redis
        otpService.saveOTPRedis(request.getEmail(), TokenType.SIGNUP_OTP);
        // ✅ Trả về thông tin user (chưa kích hoạt)
        return new UserDTO(

                newUser.getId(),
                newUser.getEmail(),
                newUser.getUsername(),
                role.getName(),
                newUser.isActivated(),
                newUser.getCreatedAt()
        );


    }

    public void requestOtpForSignup(String email) {
        if (!userRepository.existsByEmail(email)) {
            throw new AlreadyExistsException("Email không tồn tại trong hệ thống!");
        }
        otpService.requestNewOtp(email, TokenType.SIGNUP_OTP);
    }
    public boolean verifySignupOtp(String email, String userOtp) {
        boolean isValid = otpService.verifyOtp(email, userOtp, TokenType.SIGNUP_OTP);

        if (!isValid) {
            throw new InvalidOtpException("OTP không hợp lệ hoặc đã hết hạn!");
        }

        // ✅ Kích hoạt tài khoản
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản!"));

        user.setActivated(true);
        userRepository.save(user);

        // 🌟 Gửi email thông báo kích hoạt tài khoản
        emailService.sendSignupSuccessEmail(email);

        return true;
    }




     // ✅ Đăng nhập
    public AuthResponse authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email hoặc mật khẩu không chính xác!"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Email hoặc mật khẩu không chính xác!");
        }

        String token = jwtService.generateToken(user);

        return AuthResponse.builder()
                .accessToken(token)
                .userId(user.getId())
                .email(user.getEmail())
                .build();
    }

    // ✅ Gửi link đặt lại mật khẩu
    public void createAndSendResetLink(String email) {
        passwordService.createAndSendResetLink(email);
    }


    // ✅ Xác thực token & cập nhật mật khẩu
    public ResponseEntity<?> resetPassword(String email, String token, String newPassword) {
        if (!passwordService.verifyResetToken(email, token)) {
            return ResponseEntity.badRequest().body("Token không hợp lệ hoặc đã hết hạn!");
        }

        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Người dùng không tồn tại!");
        }

        User user = userOpt.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        return ResponseEntity.ok("Mật khẩu đã được cập nhật thành công!");
    }


    /*

       public UserDTO registerAdmin(RegisterRequest request) {
        // 1. Kiểm tra email đã tồn tại chưa
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email đã tồn tại!");
        }

        // 2. Mã hóa mật khẩu
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // 3. Tạo user với role ADMIN
        User admin = User.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .role("ADMIN")
                .build();

        // 4. Lưu vào database
        userRepository.save(admin);
    }

     */



  /*

    // ✅ Làm mới Access Token bằng Refresh Token
    @Override
    public AuthResponseDTO refreshToken(String refreshToken) {
        String email = jwtService.extractEmail(refreshToken);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Tài khoản không tồn tại"));

        // Cấp mới Access Token
        String newAccessToken = jwtService.generateToken(user, 15);

        return AuthResponseDTO.builder()
                .accessToken(newAccessToken)
                .refreshToken(refreshToken) // Vẫn dùng Refresh Token cũ
                .tokenType("Bearer")
                .expiresIn(15 * 60L) // 15 phút
                .build();
    }
     */

    /*
     // ✅ Đăng nhập và tạo Token
    @Override
    public AuthResponseDTO login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Email không tồn tại"));

        if (!user.isActivated()) {
            throw new IllegalArgumentException("Tài khoản chưa kích hoạt!");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Mật khẩu không chính xác!");
        }

        // Tạo Access Token & Refresh Token
        String accessToken = jwtService.generateToken(user, 15); // Access Token (15 phút)
        RefreshToken refreshToken = tokenService.createRefreshToken(user); // Lưu vào MySQL

        return AuthResponseDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .tokenType("Bearer")
                .expiresIn(15 * 60L) // 15 phút
                .build();
    }

    // ✅ Đăng xuất (Xóa RefreshToken)
    @Override
    public void logout(String refreshToken) {
        tokenService.deleteToken(refreshToken);
    }

    // ✅ Làm mới Access Token bằng Refresh Token
    @Override
    public AuthResponseDTO refreshToken(String refreshToken) {
        if (!tokenService.isTokenValid(refreshToken)) {
            throw new IllegalArgumentException("Refresh Token không hợp lệ hoặc đã hết hạn!");
        }

        RefreshToken token = tokenService.getTokenByValue(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Token không tồn tại!"));

        User user = token.getUser();

        // Nếu gần hết hạn, cập nhật thời gian
        tokenService.updateTokenExpiryIfNeeded(token);

        // Cấp mới Access Token
        String newAccessToken = jwtService.generateToken(user, 15);

        return AuthResponseDTO.builder()
                .accessToken(newAccessToken)
                .refreshToken(refreshToken) // Vẫn dùng Refresh Token cũ
                .tokenType("Bearer")
                .expiresIn(15 * 60L) // 15 phút
                .build();
    }

     */



}
