package com.example.eccommerce_ai.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Table(name = "districts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID của District

    private String name; // Tên quận/huyện

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city; // Thành phố mà quận thuộc về

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id")
    private Province province; // Tỉnh mà huyện thuộc về

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ward> wards; // Danh sách phường thuộc quận

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Commune> communes; // Danh sách xã trong huyện
}
