package com.example.eccommerce_ai.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Table(name = "emailLog")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmailLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length = 6)
    private String otpCode;

    //	Trạng thái gửi OTP: "SENT" (đã gửi) hoặc "FAILED" (gửi thất bại).
    @Column(nullable = false)
    private String status; // Trạng thái "SENT" hoặc "FAILED"


    //Thời gian gửi OTP, được mặc định là thời điểm hiện tại
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

}
