package com.example.eccommerce_ai.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_address")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID duy nhất cho mỗi địa chỉ

    private String houseNumber; // Số nhà

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user; // Thêm thuộc tính user tại đây

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ward_id")
    private Ward ward; // Phường mà địa chỉ thuộc về

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id")
    private District district; // Quận/Huyện mà địa chỉ thuộc về

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commune_id")
    private Commune commune; // Xã mà địa chỉ thuộc về

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city; // Thành phố mà địa chỉ thuộc về

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id")
    private Province province; // Tỉnh mà địa chỉ thuộc về
}
