package com.example.eccommerce_ai.repository;

import com.example.eccommerce_ai.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long > {

}
