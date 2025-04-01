package com.example.eccommerce_ai.utils;

import java.security.SecureRandom;

public class OTPGenerator {
    private static final SecureRandom random = new SecureRandom();

    // Tạo mã OTP gồm 6 chữ số
    public static String generateOTP() {
        int otp = 100000 + random.nextInt(900000); // Sinh số ngẫu nhiên từ 100000 đến 999999
        return String.valueOf(otp);
    }
}
