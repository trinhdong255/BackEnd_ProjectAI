package com.example.eccommerce_ai.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// xác thực email đổi mâtk khẩu
public class ForgotPasswordRequest {
    @Email
    @NotBlank
    private String email;
}
