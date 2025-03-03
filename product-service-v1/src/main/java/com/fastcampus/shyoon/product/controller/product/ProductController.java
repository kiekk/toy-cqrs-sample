package com.fastcampus.shyoon.product.controller.product;

import com.fastcampus.shyoon.product.dto.product.request.CreateProductRequest;
import com.fastcampus.shyoon.product.dto.product.request.UpdateProductRequest;
import com.fastcampus.shyoon.product.dto.product.response.ProductItemResponse;
import com.fastcampus.shyoon.product.dto.product.response.ProductListResponse;
import com.fastcampus.shyoon.product.entity.product.Product;
import com.fastcampus.shyoon.product.service.product.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductListResponse> getAllProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public ProductItemResponse getProductById(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody @Valid CreateProductRequest request) {
        return productService.createProduct(request.toProductEntity());
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody @Valid UpdateProductRequest request) {
        return productService.updateProduct(request.toProductEntity(id));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

}
