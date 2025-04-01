package com.example.eccommerce_ai.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Table(name = "blog")
@Data
@NoArgsConstructor
@Getter
@Setter
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính

    @Lob // Định nghĩa trường longtext
    private String content; // Nội dung bài viết

    @Column(name = "created_date") // Đặt tên cho cột trong cơ sở dữ liệu
    private LocalDate createdDate; // Ngày tạo

    @Lob // Định nghĩa trường longtext
    private String description; // Mô tả bài viết


    @Column(name = "title", length = 255) // Đặt tên và độ dài cho cột
    private String title; // Tiêu đề bài viết

    @Column(name = "user_id") // Liên kết đến bảng User
    private Long userId; // Khóa ngoại liên kết bảng User

    @Column(name = "primary_blog")
    private Integer primaryBlog; // Cờ xác định blog chính
}
