package com.cqrs.shyoon.product.query.product.controller;

import com.cqrs.shyoon.product.query.product.model.ProductItemResponse;
import com.cqrs.shyoon.product.query.product.model.ProductListResponse;
import com.cqrs.shyoon.product.query.product.service.ProductQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/query/products")
@RequiredArgsConstructor
public class ProductQueryController {

    private final ProductQueryService productQueryService;

    @GetMapping
    public List<ProductListResponse> getAllProducts() {
        return productQueryService.getProducts();
    }

    @GetMapping("/{id}")
    public ProductItemResponse getProductById(@PathVariable Long id) {
        return productQueryService.getProduct(id);
    }

}
