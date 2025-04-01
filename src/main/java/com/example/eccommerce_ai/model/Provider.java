package com.example.eccommerce_ai.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Table(name = "providers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address ;
    private String email;
    private String name;
    private String phone;

    @OneToMany(mappedBy = "provider")
    private List<ImportProduct> importProducts;
}
