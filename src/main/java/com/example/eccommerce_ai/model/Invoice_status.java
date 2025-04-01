package com.example.eccommerce_ai.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@Table(name = "invoice_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Invoice_status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "invoiceId", nullable = false)
    private Invoice invoice;

    @Column(name = "status")
    private String status;

    @Column(name = "createdDate")
    private LocalDate createdDate;
}
