package com.example.eccommerce_ai.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
//  Dữ liệu khi user đổi mật khẩu
public class ResetPasswordByTokenRequest {
    @NotBlank
    private String token;

    @NotBlank
    @Size(min = 6, max = 50)
    private String newPassword;
}
