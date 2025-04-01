package com.example.eccommerce_ai.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Table(name = "provices")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID của tỉnh

    private String name; // Tên tỉnh

    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<District> districts; // Danh sách huyện trong tỉnh
}
