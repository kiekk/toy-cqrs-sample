package com.cqrs.shyoon.product.event.product;

import com.cqrs.shyoon.product.entity.product.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductEvent {
    private ProductEventType type;
    private Product product;

    public static ProductEvent of(ProductEventType type, Product product) {
        ProductEvent event = new ProductEvent();
        event.type = type;
        event.product = product;
        return event;
    }
}
