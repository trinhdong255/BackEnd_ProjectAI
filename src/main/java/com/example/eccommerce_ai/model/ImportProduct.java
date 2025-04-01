package com.example.eccommerce_ai.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
@Entity
@Table(name = "import_products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImportProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description ;
    private Date importDate;
    private Double import_price;
    private int  quantity  ;
    private String instructionSize;
    private Integer quantitySold;
    private LocalDateTime createdDate;


    @ManyToOne
    @JoinColumn(name = "providerId")
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "sizeId")
    private Size size;

    @ManyToOne
    @JoinColumn(name = "createdUserId")
    private User createdUser;
}
