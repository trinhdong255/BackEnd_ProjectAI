package com.example.eccommerce_ai.Service;

import com.example.eccommerce_ai.Enum.TokenType;
import com.example.eccommerce_ai.Service.impleService.OtpService_Imple;
import com.example.eccommerce_ai.exception.InvalidOtpException;
import com.example.eccommerce_ai.exception.InvalidTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service

public class OtpService  implements OtpService_Imple {

    private final RedisService redisService;
    private final EmailService emailService;

    // Định nghĩa TTL cố định cho từng loại OTP
    private static final int SIGNUP_OTP_TTL = 5;     // 15 phút
    private static final int RESET_PASSWORD_TTL = 5;  // 5 phút


    public OtpService(EmailService emailService, RedisService redisService) {
        this.emailService = emailService;
        this.redisService = redisService;
    }

   // Tạo mã OTP ngẫu nhiên gồm 6 chữ số
   public String generateOtp() {
       return String.valueOf(new Random().nextInt(900000) + 100000); // OTP 6 chữ số
   }


   // Lưu OTP vào Redis với TTL và GỬI EMAIL (không chứa TokenType)

    public void saveOTPRedis(String email, TokenType tokenType) {
        String otp = generateOtp();

        // Xác định TTL theo loại OTP
        int ttlMinutes;
        switch (tokenType) {
            case SIGNUP_OTP -> ttlMinutes = SIGNUP_OTP_TTL;
            case RESET_PASSWORD -> ttlMinutes = RESET_PASSWORD_TTL;
            default -> throw new IllegalArgumentException("Loại OTP không hợp lệ!");
        }

        // Lưu OTP vào Redis
        redisService.storeOtp(email, otp, tokenType, ttlMinutes);

        // Gửi OTP qua email
        emailService.sendOtp(email, otp);
    }
    //Lấy OTP từ Redis

    public String getOtp(String email, TokenType tokenType) {
        return redisService.getOtp(email, tokenType);
    }


    // Xác thực OTP: Kiểm tra xem OTP người dùng nhập có đúng không
    public boolean verifyOtp(String email, String userOtp, TokenType tokenType) {
        String storedOtp = redisService.getOtp(email, tokenType);

        // Kiểm tra xem mã OTP có tồn tại không
        if (storedOtp == null) {
            return false; // OTP không hợp lệ hoặc đã hết hạn
        }

        // Kiểm tra xem mã OTP người dùng nhập có khớp không
        if (!storedOtp.equals(userOtp)) {
            return false; // OTP không hợp lệ
        }

        // Xóa OTP khỏi Redis sau khi xác thực thành công
        redisService.deleteOtp(email, tokenType);
        return true;
    }

    public void requestNewOtp(String email, TokenType tokenType) {
        // Kiểm tra xem OTP hiện tại còn hiệu lực không
        String existingOtp = redisService.getOtp(email, tokenType);
        if (existingOtp != null) {
            throw new InvalidOtpException("OTP hiện tại vẫn còn hiệu lực. Vui lòng thử lại sau.");
        }

        // Nếu OTP đã hết hạn, tạo và gửi OTP mới
        saveOTPRedis(email, tokenType);
    }


}
