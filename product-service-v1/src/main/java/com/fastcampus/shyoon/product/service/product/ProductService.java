package com.fastcampus.shyoon.product.service.product;

import com.fastcampus.shyoon.product.dto.product.response.ProductItemResponse;
import com.fastcampus.shyoon.product.dto.product.response.ProductListResponse;
import com.fastcampus.shyoon.product.entity.product.Product;

import java.util.List;

public interface ProductService {
    List<ProductListResponse> getProducts();

    ProductItemResponse getProduct(Long id);

    Product createProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Long id);
}
