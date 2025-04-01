package com.example.eccommerce_ai.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TokenRefreshRequestDTO {

    private String refreshToken;
}
