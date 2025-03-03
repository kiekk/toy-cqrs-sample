package com.cqrs.shyoon.product.query.product.model;

import com.cqrs.shyoon.product.command.product.model.Product;
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
