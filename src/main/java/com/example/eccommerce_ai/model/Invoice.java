package com.example.eccommerce_ai.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Entity
@Table(name = "invoice")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate createdDate;
    private Double totalAmount;
    private String payType;
    private String status;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "userAddressId")
    private UserAddress userAddress;

    private Boolean isChatbotAssisted;

    @OneToMany(mappedBy = "invoice")
    private List<InvoiceDetail> invoiceDetails;
}
