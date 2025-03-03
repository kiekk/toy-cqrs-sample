package com.cqrs.shyoon.product.listener.product;

import com.cqrs.shyoon.product.event.product.ProductEvent;
import com.cqrs.shyoon.product.repository.product.ProductQueryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductEventListener {

    private final ProductQueryRepository productQueryRepository;

    @Transactional
    @KafkaListener(topics = "products", groupId = "products-group")
    public void processProductEvent(String event) {

        log.info("Getting event {}", event);

        ProductEvent productEvent;
        try {
            productEvent = new ObjectMapper().readValue(event, ProductEvent.class);

            log.info("parsed event: {}", productEvent);

            switch (productEvent.getType()) {
                case CREATE:
                    productQueryRepository.save(productEvent.getProduct());
                    break;

                case UPDATE:
                    productQueryRepository.save(productEvent.getProduct());
                    break;

                case DELETE:
                    productQueryRepository.deleteById(productEvent.getProduct().getId());
                    break;
                default:
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
