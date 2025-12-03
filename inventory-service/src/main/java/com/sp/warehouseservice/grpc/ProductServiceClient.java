package com.sp.warehouseservice.grpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sp.warehouse.GetProductsResponse;
import com.sp.warehouse.GetProductsRequest;
import com.sp.warehouse.ProductServiceGrpc;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceClient {
    private static final Logger log = LoggerFactory.getLogger(ProductServiceClient.class);
    private final ProductServiceGrpc.ProductServiceBlockingStub blockingStub;

    public ProductServiceClient(
            @Value("${product.service.address:localhost}") String serverAddress,
            @Value("${product.service.grpc.port:9001}")    int serverPort
    ) {
        log.info("init ProductServiceClient");
        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverAddress, serverPort).usePlaintext().build();
        blockingStub = ProductServiceGrpc.newBlockingStub(channel);
    }

    public GetProductsResponse getProductDetailsResponse(List<String> productIds){
        GetProductsRequest request = GetProductsRequest.newBuilder()
                .addAllProductIds(productIds)
                .build();
        GetProductsResponse response = blockingStub.getProducts(request);
        log.info("Fetched all product details for productIds {}", response);
        return response;
    }
}
