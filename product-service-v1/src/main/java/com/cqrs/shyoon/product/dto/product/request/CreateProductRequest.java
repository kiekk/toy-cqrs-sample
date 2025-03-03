package com.cqrs.shyoon.product.dto.product.request;

import com.cqrs.shyoon.product.entity.product.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateProductRequest {
    @NotBlank(message = "상품명은 필수값입니다.")
    private String name;

    @NotNull(message = "상품 가격은 필수값입니다.")
    @Min(value = 0, message = "상품 가격은 0원 이상이어야 합니다.")
    private Integer price;

    public Product toProductEntity() {
        return Product.builder()
                .name(name)
                .price(price)
                .build();
    }
}
