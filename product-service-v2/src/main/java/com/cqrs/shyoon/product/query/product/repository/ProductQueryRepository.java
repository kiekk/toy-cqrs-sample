package com.cqrs.shyoon.product.query.product.repository;

import com.cqrs.shyoon.product.command.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductQueryRepository extends JpaRepository<Product, Long> {
}
