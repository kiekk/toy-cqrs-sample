package com.fastcampus.shyoon.product.entity.product;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "product")
@NoArgsConstructor
public class Product {
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

}