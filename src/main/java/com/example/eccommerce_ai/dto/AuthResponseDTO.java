package com.example.eccommerce_ai.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDTO {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private Long expiresIn;
}
