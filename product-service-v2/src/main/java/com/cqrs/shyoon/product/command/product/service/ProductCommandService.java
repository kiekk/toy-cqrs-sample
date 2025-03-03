package com.cqrs.shyoon.product.command.product.service;

import com.cqrs.shyoon.product.command.product.model.Product;

public interface ProductCommandService {
    Product createProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Long id);
}
