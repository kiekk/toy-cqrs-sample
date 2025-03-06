package com.cqrs.shyoon.product.entity.product;

import com.cqrs.shyoon.product.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "product")
@NoArgsConstructor
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // 상품명
    private int price; // 가격

    @Builder
    private Product(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public static Product create(String name, int price) {
        return Product.builder()
                .name(name)
                .price(price)
                .build();
    }

    public static Product update(Long id, String name, int price) {
        return Product.builder()
                .id(id)
                .name(name)
                .price(price)
                .build();
    }

    public static Product delete(Long id) {
        return Product.builder()
                .id(id)
                .build();
    }

}
