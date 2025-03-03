package com.cqrs.shyoon.product.model.product;

import com.cqrs.shyoon.product.entity.product.Product;
import lombok.Getter;

@Getter
public class ProductListResponse {
    private final Long id;
    private final String name;
    private final int price;

    private ProductListResponse(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public static ProductListResponse fromProductEntity(Product product) {
        return new ProductListResponse(product.getId(), product.getName(), product.getPrice());
    }
}
