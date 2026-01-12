package com.sp.product.kafka.producer;

import com.example.productProto.ProductsEvents;
import com.sp.product.event.ProductCreatedInternalEvent;
import com.sp.product.mapper.ProductMapper;
import com.sp.product.mapper.StockThresholdMapper;
import com.sp.product.mapper.SupplierProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductProducer {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public ProductProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendProductCreated(
           ProductCreatedInternalEvent productCreatedInternalEvent
    ) {
        ProductsEvents.ProductCreatedEvent events = ProductMapper.toProductCreatedEvent(
                productCreatedInternalEvent.productId(),
                SupplierProductMapper.toSupplierEvent(productCreatedInternalEvent.productRequest().getSupplierProductDetail()),
                productCreatedInternalEvent.productRequest().getStockThresholdRequestDTOS()
                        .stream()
                        .map(StockThresholdMapper::toStockThresholdEvent)
                        .collect(Collectors.toSet())
        );

        kafkaTemplate.send(
                  "product.created",
                        String.valueOf(productCreatedInternalEvent.productId()) ,
                        events.toByteArray()
                ).whenComplete((res, ex) -> {
                    if (ex != null) {
                        log.error(ex.getMessage(), ex);
                    }
                });
    }

}
