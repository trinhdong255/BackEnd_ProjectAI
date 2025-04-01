package com.example.eccommerce_ai.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name ;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "code")
    private String code;


    @Column(name = "price")
    private Double price;

    @Column(name = "instructionSize")
    private String instructionSize;

    @Column(name = "quantitySold")
    private Integer quantitySold;

    @Column(name = "createdDate")
    private LocalDateTime createdDate;

    @Column(name = "deleted")
    private Boolean deleted;

    // Thêm danh sách hình ảnh
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true) // Mối quan hệ với ProductImg
    private List<ProductImage> productImages;

    // Thêm danh sách kích thước
    @OneToMany(mappedBy = "product") // Mối quan hệ với Size
    private List<Size> sizes;

    @ManyToOne
    @JoinColumn(name = "trademarkId")
    private Trademark trademark;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

}
