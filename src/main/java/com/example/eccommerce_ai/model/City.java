package com.example.eccommerce_ai.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "citys")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID của thành phố

    private String name; // Tên thành phố

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<District> districts; // Các quận trong thành phố
}
