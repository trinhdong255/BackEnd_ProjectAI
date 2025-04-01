package com.example.eccommerce_ai.Service;

import com.example.eccommerce_ai.Service.impleService.TokenService_Imple;
import com.example.eccommerce_ai.model.RefreshToken;
import com.example.eccommerce_ai.model.User;
import com.example.eccommerce_ai.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class TokenService {



    /*

     implements TokenService_Imple

        private final RefreshTokenRepository refreshTokenRepository;

    // ⏳ Thời gian duy trì của RefreshToken (7 ngày)
    private static final int REFRESH_TOKEN_TTL = 7 * 24 * 60; // 7 ngày (phút)

    // ✅ 1. Tạo mới RefreshToken khi user đăng nhập
    @Override
    public RefreshToken createRefreshToken(User user) {
        // Xóa tất cả token cũ của user trước khi tạo mới (tùy chọn)
        refreshTokenRepository.deleteByUser(user);

        // Tạo token mới
        RefreshToken refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString()) // Sinh token ngẫu nhiên
                .user(user)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(REFRESH_TOKEN_TTL)) // 7 ngày
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    // ✅ 2. Kiểm tra xem token có hợp lệ không
    @Override
    public boolean isTokenValid(String token) {
        return refreshTokenRepository.findByToken(token)
                .map(rt -> !rt.isExpired()) // Nếu token hết hạn → false
                .orElse(false); // Nếu không tìm thấy token → false
    }

    // ✅ 3. Lấy danh sách tất cả RefreshToken của một user
    @Override
    public List<RefreshToken> getTokensByUser(User user) {
        return refreshTokenRepository.findByUser(user);
    }

    // ✅ 4. Cập nhật thời gian `expiresAt` nếu cần
    @Override
    public void updateTokenExpiryIfNeeded(RefreshToken token) {
        long remainingMinutes = ChronoUnit.MINUTES.between(LocalDateTime.now(), token.getExpiresAt());

        // Nếu token còn < 1 ngày thì gia hạn thêm
        if (remainingMinutes < (24 * 60)) {
            token.setExpiresAt(LocalDateTime.now().plusMinutes(REFRESH_TOKEN_TTL)); // Cập nhật lại thời gian hết hạn
            refreshTokenRepository.save(token);
        }
    }

    // ✅ 5. Xóa tất cả token đã hết hạn
    @Override
    public void deleteExpiredTokens() {
        refreshTokenRepository.deleteAllExpiredTokens(LocalDateTime.now());
    }

    // ✅ 6. Xóa một RefreshToken cụ thể (khi user đăng xuất)
    @Override
    public void deleteToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }



     */

}
