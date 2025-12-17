package com.sp.partners.grpc;

import com.sp.addressGrpcService.AddressGRPCRequest;
import com.sp.addressGrpcService.AddressGRPCResponse;
import com.sp.addressGrpcService.AddressServiceGrpc;
import com.sp.partners.dto.AddressRequestDTO;
import com.sp.partners.mapper.AddressMapper;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceClient {

    private final AddressServiceGrpc.AddressServiceBlockingStub addressServiceBlockingStub;

    public AddressServiceClient(
            @Value("${common.service.address:localhost}") String host,
            @Value("${common.service.grpc.port:9003}")    int port
    ) {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        addressServiceBlockingStub = AddressServiceGrpc.newBlockingStub(managedChannel);
    }

    public AddressGRPCResponse createAddress(AddressGRPCRequest request) {

        AddressGRPCResponse createdAddress = addressServiceBlockingStub
                                                .createAddress(request);

        if (createdAddress == null) {
            throw new RuntimeException("create address failed");
        }

        return createdAddress;

    }
}
