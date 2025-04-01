package com.example.eccommerce_ai.Service;

import com.example.eccommerce_ai.Enum.TokenType;
import com.example.eccommerce_ai.Service.impleService.PasswordService_Imple;
import com.example.eccommerce_ai.model.User;
import com.example.eccommerce_ai.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

// Đổi mật khẩu, đặt lại mật khẩu
@Service
@RequiredArgsConstructor
public class PasswordService  {

    private final RedisService redisService;
    private final EmailService emailService;

    private static final int RESET_TOKEN_TTL = 15; // Thời gian sống của Reset Token (15 phút)

    /**
     * Tạo Reset Token và gửi link đặt lại mật khẩu qua email
     */
    public void createAndSendResetLink(String email) {
        //  Xóa Reset Token cũ nếu có
        redisService.deleteOtp(email, TokenType.RESET_PASSWORD);

        // ✅ Tạo Reset Token (UUID ngẫu nhiên)
        String resetToken = UUID.randomUUID().toString();

        // ✅ Lưu Reset Token vào Redis (Thời gian sống: 15 phút)
        redisService.storeOtp(email, resetToken, TokenType.RESET_PASSWORD, RESET_TOKEN_TTL);

        // ✅ Gửi email chứa link đặt lại mật khẩu
        String resetLink = "https://yourfrontend.com/reset-password?token=" + resetToken;
        emailService.sendPasswordResetEmail(email, resetLink);

    }



    /**
     * Xác thực Reset Token từ đường link (Frontend gửi request với token)
     */
    public boolean verifyResetToken(String email, String token) {
        String storedToken = redisService.getOtp(email, TokenType.RESET_PASSWORD);

        // Kiểm tra xem token có tồn tại hay không
        if (storedToken == null) {
            // Thông báo rõ ràng nếu không tìm thấy token
            System.out.println("Token không tồn tại hoặc đã hết hạn.");
            return false; // Token không hợp lệ
        }

        // Kiểm tra xem token có khớp với token đã gửi không
        if (!storedToken.equals(token)) {
            // Thông báo rõ ràng nếu token không khớp
            System.out.println("Token không hợp lệ.");
            return false; // Token không hợp lệ
        }

        // Nếu token hợp lệ, xóa token đã xác thực
        redisService.deleteOtp(email, TokenType.RESET_PASSWORD);
        return true; // Token hợp lệ
    }


}
