package com.cqrs.shyoon.product.service.product.impl;

import com.cqrs.shyoon.product.model.product.ProductItemResponse;
import com.cqrs.shyoon.product.model.product.ProductListResponse;
import com.cqrs.shyoon.product.repository.product.ProductQueryRepository;
import com.cqrs.shyoon.product.service.product.ProductQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductQueryServiceImpl implements ProductQueryService {

    private final ProductQueryRepository productQueryRepository;

    @Override
    public List<ProductListResponse> getProducts() {
        return productQueryRepository.findAll().stream()
                .map(ProductListResponse::fromProductEntity)
                .toList();
    }

    @Override
    public ProductItemResponse getProduct(Long id) {
        return ProductItemResponse.fromProductEntity(
                productQueryRepository.findById(id)
                        .orElseThrow(() ->
                                new IllegalArgumentException("해당 상품이 없습니다. id=" + id)));
    }
}
