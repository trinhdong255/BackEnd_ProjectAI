package com.example.eccommerce_ai.Service.impleService;

public interface PasswordService_Imple {

    void  sendPasswordResetLink(String email); // ✅ Gửi OTP đặt lại mật khẩu
    void resetPassword(String email, String newPassword); // ✅ Xác thực OTP & đổi mật khẩu

}
