package com.sp.inventory.kafka.consumer;

import com.google.protobuf.InvalidProtocolBufferException;
import com.sp.inventory.service.InventoryProvisionService;
import com.sp.inventoryProto.ProductsEvents;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ProductConsumer {

    private final InventoryProvisionService inventoryProvisionService;

    public ProductConsumer(
          InventoryProvisionService inventoryProvisionService
    ) {
        this.inventoryProvisionService = inventoryProvisionService;
    }

    @RetryableTopic(
            attempts = "5",
            backoff = @Backoff(delay = 2000),
            dltTopicSuffix = ".DLT"
    )
    @KafkaListener(topics = "product.created" , groupId = "inventory-service")
    @Transactional
    public void consumeProductCreatedEvent(byte[] event) throws InvalidProtocolBufferException {

        ProductsEvents.ProductCreatedEvent parsedEvent = ProductsEvents.ProductCreatedEvent.parseFrom(event);
        inventoryProvisionService.provision(parsedEvent);

    }

}
