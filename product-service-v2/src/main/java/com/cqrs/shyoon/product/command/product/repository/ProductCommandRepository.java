package com.cqrs.shyoon.product.command.product.repository;

import com.cqrs.shyoon.product.command.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCommandRepository extends JpaRepository<Product, Long> {
}
