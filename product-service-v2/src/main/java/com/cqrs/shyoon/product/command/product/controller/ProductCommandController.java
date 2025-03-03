package com.cqrs.shyoon.product.command.product.controller;

import com.cqrs.shyoon.product.command.product.model.CreateProductRequest;
import com.cqrs.shyoon.product.command.product.model.Product;
import com.cqrs.shyoon.product.command.product.model.UpdateProductRequest;
import com.cqrs.shyoon.product.command.product.service.ProductCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/command/products")
@RequiredArgsConstructor
public class ProductCommandController {

    private final ProductCommandService productCommandService;

    @PostMapping
    public Product createProduct(@RequestBody @Valid CreateProductRequest request) {
        return productCommandService.createProduct(request.toProductEntity());
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody @Valid UpdateProductRequest request) {
        return productCommandService.updateProduct(request.toProductEntity(id));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productCommandService.deleteProduct(id);
    }
}
