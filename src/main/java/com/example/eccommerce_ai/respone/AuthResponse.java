package com.example.eccommerce_ai.respone;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
    private String accessToken;

    @Builder.Default
    private String tokenType = "Bearer"; // Luôn là "Bearer"
    private Long userId;
    private String email;



}
