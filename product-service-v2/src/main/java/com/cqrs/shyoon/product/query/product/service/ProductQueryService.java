package com.cqrs.shyoon.product.query.product.service;

import com.cqrs.shyoon.product.query.product.model.ProductItemResponse;
import com.cqrs.shyoon.product.query.product.model.ProductListResponse;

import java.util.List;

public interface ProductQueryService {
    List<ProductListResponse> getProducts();

    ProductItemResponse getProduct(Long id);
}
