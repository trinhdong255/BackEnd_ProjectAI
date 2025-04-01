package com.example.eccommerce_ai.Enum;

public enum TokenType {
    ACCESS,         // Không lưu, chỉ trả về client
    SIGNUP_OTP,     // OTP khi đăng ký
    RESET_PASSWORD, // OTP khi quên mật khẩu
    REFRESH_TOKEN   // Token duy trì đăng nhập
}
