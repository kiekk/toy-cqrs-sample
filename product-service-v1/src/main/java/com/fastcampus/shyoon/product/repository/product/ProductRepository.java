package com.fastcampus.shyoon.product.repository.product;

import com.fastcampus.shyoon.product.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
