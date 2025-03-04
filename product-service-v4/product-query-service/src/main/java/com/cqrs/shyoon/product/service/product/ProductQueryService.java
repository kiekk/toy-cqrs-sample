package com.cqrs.shyoon.product.service.product;

import com.cqrs.shyoon.product.model.product.ProductItemResponse;
import com.cqrs.shyoon.product.model.product.ProductListResponse;

import java.util.List;

public interface ProductQueryService {
    List<ProductListResponse> getProducts();

    ProductItemResponse getProduct(Long id);
}
