package com.example.eccommerce_ai.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Table(name = "cart")
@Data
@NoArgsConstructor
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "sizeId")
    private Size size;

    private int quantity;
}
