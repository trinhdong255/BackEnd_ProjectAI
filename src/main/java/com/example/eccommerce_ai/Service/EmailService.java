package com.example.eccommerce_ai.Service;

import com.example.eccommerce_ai.Enum.TokenType;
import com.example.eccommerce_ai.Service.impleService.EmailService_Imple;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService implements EmailService_Imple {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}") // Email gửi OTP
    private String fromEmail;

    public void sendOtp(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(" Mã xác thực OTP của bạn");
        message.setText("Mã OTP của bạn là: " + otp + "\nOTP này có hiệu lực trong thời gian giới hạn.");
        mailSender.send(message);
    }

    @Override
    public void sendPasswordResetEmail(String email, String resetLink) {
        String subject = "Yêu cầu đặt lại mật khẩu";
        String body = "<p>Bạn đã yêu cầu đặt lại mật khẩu.</p>" +
                "<p>Nhấp vào link dưới đây để đặt lại mật khẩu:</p>" +
                "<a href=\"" + resetLink + "\">Đặt lại mật khẩu</a>" +
                "<p>Liên kết có hiệu lực trong 30 phút.</p>";

        sendEmail(email, subject, body);
    }


    public void sendSignupSuccessEmail(String email) {
        String subject = "Chúc mừng! Tài khoản của bạn đã được kích hoạt";
        String body = "<p>Xin chào,</p>" +
                "<p>Tài khoản của bạn đã được kích hoạt thành công.</p>" +
                "<p>Bạn có thể đăng nhập và sử dụng ngay bây giờ.</p>" +
                "<p>Trân trọng,</p>" +
                "<p><b>Đội ngũ Hỗ trợ</b></p>";

        sendEmail(email, subject, body);
    }
    private void sendEmail(String to, String subject, String body) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true); // true -> gửi HTML

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Lỗi gửi email: " + e.getMessage());
        }
    }
}
