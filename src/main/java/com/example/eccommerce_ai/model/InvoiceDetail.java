package com.example.eccommerce_ai.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "invoice_detail")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InvoiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "invoiceId", nullable = false)
    private Invoice invoice;


    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "sizeId")
    private Size size;


    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Double price;
}
