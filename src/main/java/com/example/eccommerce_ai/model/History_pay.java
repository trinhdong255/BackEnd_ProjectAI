package com.example.eccommerce_ai.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "history_pay")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class History_pay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "createdDate")
    private LocalDate created_date ;

    @Column(name = "totalAmount")
    private Double total_amount;

    @ManyToOne
    @JoinColumn(name = "invoiceId")
    private Invoice invoice;

    @Column(name = "createdTime")
    private LocalDateTime createdTime;
}
