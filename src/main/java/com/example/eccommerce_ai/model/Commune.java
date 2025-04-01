package com.example.eccommerce_ai.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "communes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Commune {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID của Commune

    private String name; // Tên xã

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id")
    private District district; // Quận mà xã thuộc về
}
