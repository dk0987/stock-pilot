package com.sp.productservice.grpc;

import com.sp.product.GetProductsRequest;
import com.sp.product.GetProductsResponse;
import com.sp.product.ProductDetail;
import com.sp.product.ProductServiceGrpc;
import com.sp.productservice.model.Product;
import com.sp.productservice.service.ProductService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@GrpcService
public class ProductServiceServer extends ProductServiceGrpc.ProductServiceImplBase {
    private static final Logger log = LoggerFactory.getLogger(ProductServiceServer.class);
    private final ProductService productService;
    public ProductServiceServer(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void getProducts(GetProductsRequest request, StreamObserver<GetProductsResponse> responseObserver) {
        log.info("getProducts request received");
        List<Product> products = productService.getProductDetails(request.getProductIdsList().stream().map(UUID::fromString).toList());
        List<ProductDetail> productResponse = new ArrayList<>();
        log.info("getProducts response{}",productResponse);
        products.forEach(product -> {
            ProductDetail internalProduct = ProductDetail.newBuilder()
                    .setProductId(product.getId().toString())
                    .setProductName(product.getName())
                    .setProductDescription(product.getDescription())
                    .setProductCategory(product.getCategory().getName())
                    .setProductMaxOccupancy(product.getStock())
                    .build();
            productResponse.add(internalProduct);
        });
        log.info("getProducts response push{}",productResponse);
        responseObserver.onNext(GetProductsResponse.newBuilder().addAllProducts(productResponse).build());
        responseObserver.onCompleted();
    }

}
