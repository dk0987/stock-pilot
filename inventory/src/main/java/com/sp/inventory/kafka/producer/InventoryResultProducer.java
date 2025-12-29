package com.sp.inventory.kafka.producer;

import com.example.product.InventoryProvisionEvents;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InventoryResultProducer {

    private final KafkaTemplate<String, byte[] > kafkaTemplate;

    public InventoryResultProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendStockThresholdSuccessful(
            Long productId
    ){
        InventoryProvisionEvents.InventoryProvisioned event = InventoryProvisionEvents.InventoryProvisioned
                .newBuilder()
                .setProductId(productId)
                .build();

        try {

            kafkaTemplate.send("inventory.provisioned" ,String.valueOf(productId), event.toByteArray());

        } catch (RuntimeException e) {

            log.error("Failed to send stock threshold event", e);

        }
    }

    public void sendStockThresholdFailed(
            Long productId,
            String reason
    ){
        InventoryProvisionEvents.InventoryFailed event = InventoryProvisionEvents.InventoryFailed
                .newBuilder()
                .setProductId(productId)
                .setReason(reason)
                .build();

        try {

            kafkaTemplate.send("inventory.failed" ,String.valueOf(productId) ,event.toByteArray());

        } catch (RuntimeException e) {

            log.error("Failed to send stock threshold event", e);

        }

    }
}
