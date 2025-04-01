package com.example.eccommerce_ai.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "wards")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "name")
    private String name; // Tên phường

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id")
    private District district; // Quận mà phường thuộc về
}
