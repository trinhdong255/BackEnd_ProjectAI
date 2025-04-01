package com.example.eccommerce_ai.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Table(name = "Trade_mark")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Trademark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name ;

    @OneToMany(mappedBy = "trademark")
    private List<Product> products;
}
