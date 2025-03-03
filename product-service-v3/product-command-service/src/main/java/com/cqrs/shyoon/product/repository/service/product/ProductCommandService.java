package com.cqrs.shyoon.product.repository.service.product;

import com.cqrs.shyoon.product.entity.product.Product;

public interface ProductCommandService {
    Product createProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Long id);
}
