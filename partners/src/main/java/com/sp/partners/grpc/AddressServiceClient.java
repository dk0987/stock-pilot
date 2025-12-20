package com.sp.partners.grpc;

import com.sp.partners.dto.AddressRequestDTO;
import com.sp.partners.mapper.AddressMapper;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AddressServiceClient {

    private final com.sp.partnersGrpcService.AddressServiceGrpc.AddressServiceBlockingStub addressServiceBlockingStub;

    public AddressServiceClient(
            @Value("${common.service.address:localhost}") String host,
            @Value("${common.service.grpc.port:9003}")    int port
    ) {

        log.info("AddressServiceClient init...");
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        addressServiceBlockingStub = com.sp.partnersGrpcService.AddressServiceGrpc.newBlockingStub(managedChannel);
    }

    public com.sp.partnersGrpcService.AddressGRPCResponse createAddress(com.sp.partnersGrpcService.AddressGRPCRequest request) {

        log.info("AddressServiceClient createAddress...");
        com.sp.partnersGrpcService.AddressGRPCResponse createdAddress = addressServiceBlockingStub
                                                .createAddress(request);

        if (createdAddress == null) {
            throw new RuntimeException("create address failed");
        }

        return createdAddress;

    }
}
