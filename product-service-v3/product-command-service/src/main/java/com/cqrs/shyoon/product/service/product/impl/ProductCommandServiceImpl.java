package com.cqrs.shyoon.product.service.product.impl;

import com.cqrs.shyoon.product.entity.product.Product;
import com.cqrs.shyoon.product.event.product.ProductEvent;
import com.cqrs.shyoon.product.repository.product.ProductCommandRepository;
import com.cqrs.shyoon.product.service.product.ProductCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.cqrs.shyoon.product.event.product.ProductEventType.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductCommandServiceImpl implements ProductCommandService {

    private final ProductCommandRepository productCommandRepository;
    private final KafkaTemplate<String, ProductEvent> kafkaTemplate;

    @Override
    public Product createProduct(Product product) {
        Product createdProduct = productCommandRepository.save(product);

        // kafka로 상품 생성 이벤트 발행
        kafkaTemplate.send("products", ProductEvent.of(CREATE, Product.create(createdProduct.getName(), createdProduct.getPrice())));
        return createdProduct;
    }

    @Override
    public Product updateProduct(Product product) {
        productCommandRepository.findById(product.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + product.getId()));
        Product updatedProduct = productCommandRepository.save(product);

        // kafka로 상품 수정 이벤트 발행
        kafkaTemplate.send("products", ProductEvent.of(UPDATE, Product.update(product.getId(), product.getName(), product.getPrice())));
        return updatedProduct;
    }

    @Override
    public void deleteProduct(Long id) {
        productCommandRepository.deleteById(id);

        // kafka로 상품 삭제 이벤트 발행
        kafkaTemplate.send("products", ProductEvent.of(DELETE, Product.delete(id)));
    }

}
