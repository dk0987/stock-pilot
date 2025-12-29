package com.sp.inventory.kafka.consumer;

import com.example.inventoryProto.ProductsEvents;
import com.google.protobuf.InvalidProtocolBufferException;
import com.sp.inventory.service.InventoryProvisionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
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

    @KafkaListener(topics = "product.created" , groupId = "inventory-service")
    @Transactional
    public void consumeProductCreatedEvent(byte[] event) throws InvalidProtocolBufferException {

        ProductsEvents.ProductCreatedEvent parsedEvent = ProductsEvents.ProductCreatedEvent.parseFrom(event);
        inventoryProvisionService.provision(parsedEvent);

    }

}
