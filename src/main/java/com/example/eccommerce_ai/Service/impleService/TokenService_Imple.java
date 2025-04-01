package com.example.eccommerce_ai.Service.impleService;

import com.example.eccommerce_ai.dto.UserTokenDTO;
import com.example.eccommerce_ai.model.RefreshToken;
import com.example.eccommerce_ai.model.User;

import java.util.List;
import java.util.Optional;

public interface TokenService_Imple {

    RefreshToken createRefreshToken(User user);
    boolean isTokenValid(String token);
    List<RefreshToken> getTokensByUser(User user);
    void updateTokenExpiryIfNeeded(RefreshToken token);
    void deleteExpiredTokens();
    void deleteToken(String token);
}
