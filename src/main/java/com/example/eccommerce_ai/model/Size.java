package com.example.eccommerce_ai.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Table(name = "size")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @OneToMany(mappedBy = "size")
    private List<Cart> carts;

    @OneToMany(mappedBy = "size")
    private List<InvoiceDetail> invoiceDetails;

}
