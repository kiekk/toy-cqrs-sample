package com.cqrs.shyoon.product.repository.product;

import com.cqrs.shyoon.product.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
