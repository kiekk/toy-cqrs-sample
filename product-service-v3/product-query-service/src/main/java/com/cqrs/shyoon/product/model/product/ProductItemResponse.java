package com.cqrs.shyoon.product.model.product;

import com.cqrs.shyoon.product.entity.product.Product;
import lombok.Getter;

@Getter
public class ProductItemResponse {
    private final Long id;
    private final String name;
    private final int price;

    private ProductItemResponse(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public static ProductItemResponse fromProductEntity(Product product) {
        return new ProductItemResponse(product.getId(), product.getName(), product.getPrice());
    }
}
