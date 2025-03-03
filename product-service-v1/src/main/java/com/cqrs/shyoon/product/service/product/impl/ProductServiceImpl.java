package com.cqrs.shyoon.product.service.product.impl;

import com.cqrs.shyoon.product.dto.product.response.ProductItemResponse;
import com.cqrs.shyoon.product.dto.product.response.ProductListResponse;
import com.cqrs.shyoon.product.entity.product.Product;
import com.cqrs.shyoon.product.repository.product.ProductRepository;
import com.cqrs.shyoon.product.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ProductListResponse> getProducts() {
        return productRepository.findAll().stream()
                .map(ProductListResponse::fromProductEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public ProductItemResponse getProduct(Long id) {
        return ProductItemResponse.fromProductEntity(
                productRepository.findById(id)
                        .orElseThrow(() ->
                                new IllegalArgumentException("해당 상품이 없습니다. id=" + id)));
    }

    @Transactional
    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    @Override
    public Product updateProduct(Product product) {
        Product findProduct = productRepository.findById(product.getId())
                .orElseThrow(() ->
                        new IllegalArgumentException("해당 상품이 없습니다. id=" + product.getId()));

        return productRepository.save(product);
    }

    @Transactional
    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
