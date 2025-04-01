package com.example.eccommerce_ai.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_img") // Tên bảng trong cơ sở dữ liệu
@Data
@NoArgsConstructor
@Getter
@Setter
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính

    @Column(name = "linkImage", nullable = false) // Đường dẫn tới hình ảnh sản phẩm
    private String linkImage;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = false) // Khóa ngoại liên kết với bảng Product
    private Product product; // Mối quan hệ với entity Product
}
