package com.cqrs.shyoon.product.controller.product;

import com.cqrs.shyoon.product.model.product.ProductItemResponse;
import com.cqrs.shyoon.product.model.product.ProductListResponse;
import com.cqrs.shyoon.product.repository.service.product.ProductQueryService;
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
