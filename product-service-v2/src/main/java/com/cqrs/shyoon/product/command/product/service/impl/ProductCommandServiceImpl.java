package com.cqrs.shyoon.product.command.product.service.impl;

import com.cqrs.shyoon.product.command.product.model.Product;
import com.cqrs.shyoon.product.command.product.repository.ProductCommandRepository;
import com.cqrs.shyoon.product.command.product.service.ProductCommandService;
import com.cqrs.shyoon.product.query.product.repository.ProductQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductCommandServiceImpl implements ProductCommandService {

    private final ProductCommandRepository productCommandRepository;
    private final ProductQueryRepository productQueryRepository;

    @Override
    public Product createProduct(Product product) {
        Product savedProduct = productCommandRepository.save(product);

        // read db 동기화
        Product productForSync = Product.builder()
                .name(product.getName())
                .price(product.getPrice())
                .build();
        productQueryRepository.save(productForSync);
        return savedProduct;
    }

    @Override
    public Product updateProduct(Product product) {
        productCommandRepository.findById(product.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + product.getId()));
        Product updatedProduct = productCommandRepository.save(product);

        // read db 동기화
        Product productForSync = Product.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();
        productQueryRepository.save(productForSync);
        return updatedProduct;
    }

    @Override
    public void deleteProduct(Long id) {
        productCommandRepository.deleteById(id);

        // read db 동기화
        productQueryRepository.deleteById(id);
    }
}
