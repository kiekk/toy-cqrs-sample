package com.cqrs.shyoon.product.service.product;

import com.cqrs.shyoon.product.dto.product.response.ProductItemResponse;
import com.cqrs.shyoon.product.dto.product.response.ProductListResponse;
import com.cqrs.shyoon.product.entity.product.Product;

import java.util.List;

public interface ProductService {
    List<ProductListResponse> getProducts();

    ProductItemResponse getProduct(Long id);

    Product createProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Long id);
}
