package com.cqrs.shyoon.product.repository.service.product.impl;

import com.cqrs.shyoon.product.entity.product.Product;
import com.cqrs.shyoon.product.repository.product.ProductCommandRepository;
import com.cqrs.shyoon.product.repository.service.product.ProductCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductCommandServiceImpl implements ProductCommandService {

    private final ProductCommandRepository productCommandRepository;

    @Override
    public Product createProduct(Product product) {
        return productCommandRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        productCommandRepository.findById(product.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + product.getId()));
        return productCommandRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productCommandRepository.deleteById(id);
    }

}
