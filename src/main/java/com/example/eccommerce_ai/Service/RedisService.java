package com.example.eccommerce_ai.Service;

import com.example.eccommerce_ai.Enum.TokenType;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


@Service

public class RedisService  {

    private final StringRedisTemplate redisTemplate;


    public RedisService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * Lưu OTP vào Redis với thời gian sống (TTL)
     * Nếu đã có OTP cũ, xóa trước khi lưu mới
     */
    public void storeOtp(String email, String otp, TokenType tokenType, int ttlMinutes) {
        String key = tokenType.name() + "_" + email;

        // 🛑 Xóa OTP cũ trước khi lưu mới (đảm bảo không có trùng lặp)
        redisTemplate.delete(key);

        // ✅ Lưu OTP mới vào Redis với TTLa
        redisTemplate.opsForValue().set(key, otp, Duration.ofMinutes(ttlMinutes));
    }

    /**
     * Lấy OTP từ Redis
     */
    public String getOtp(String email, TokenType tokenType) {
        String key = tokenType.name() + "_" + email;
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * Xóa OTP khỏi Redis (Sau khi xác thực thành công hoặc khi user yêu cầu mã mới)
     */
    public void deleteOtp(String email, TokenType tokenType) {
        String key = tokenType.name() + "_" + email;
        redisTemplate.delete(key);
    }
}
