package com.sp.warehouseservice.grpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceClient {
    private static final Logger log = LoggerFactory.getLogger(ProductServiceClient.class);
    private final com.sp.warehouse.ProductServiceGrpc.ProductServiceBlockingStub blockingStub;

    public ProductServiceClient(
            @Value("${product.service.address:localhost}") String serverAddress,
            @Value("${product.service.grpc.port:9001}")    int serverPort
    ) {
        log.info("init ProductServiceClient");
        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverAddress, serverPort).usePlaintext().build();
        blockingStub = com.sp.warehouse.ProductServiceGrpc.newBlockingStub(channel);
    }

    public com.sp.warehouse.GetProductsResponse getProductDetailsResponse(List<String> productIds){
        log.info("Method called");
        com.sp.warehouse.GetProductsRequest request = com.sp.warehouse.GetProductsRequest.newBuilder()
                .addAllProductIds(productIds)
                .build();
        log.info("Method called2{}",request);
        com.sp.warehouse.GetProductsResponse response = blockingStub.getProducts(request);
        log.info("Fetched all product details for productIds {}", response);
        return response;
    }
}
