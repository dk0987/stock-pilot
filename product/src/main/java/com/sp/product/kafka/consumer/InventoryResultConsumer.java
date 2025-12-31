package com.sp.product.kafka.consumer;

import com.example.product.InventoryProvisionEvents;
import com.google.protobuf.InvalidProtocolBufferException;
import com.sp.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class InventoryResultConsumer {

    private final ProductService productService;

    public InventoryResultConsumer(ProductService productService) {
        this.productService = productService;
    }

    @RetryableTopic(
            attempts = "5",
            backoff = @Backoff(delay = 2000),
            dltTopicSuffix = ".DLT"
    )
    @KafkaListener(topics = "inventory.failed" , groupId = "product-service")
    @Transactional
    public void consumerStockThresholdFailedEvent(byte[] event ) throws InvalidProtocolBufferException {
        InventoryProvisionEvents.InventoryFailed consumedEvent = InventoryProvisionEvents
                .InventoryFailed.parseFrom(event);

        productService.deleteProduct(consumedEvent.getProductId());

        log.info(consumedEvent.getReason());

    }

}
