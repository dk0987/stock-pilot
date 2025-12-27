package com.sp.warehouse.grpc;

import com.sp.warehouse.dto.AddressRequestDTO;
import com.sp.warehouse.dto.AddressResponseDTO;
import com.sp.warehouse.mapper.AddressMapper;
import com.sp.warehouse.mapper.WarehouseMapper;
import com.sp.warehouseGrpcService.AddressGRPCResponse;
import com.sp.warehouseGrpcService.AddressServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AddressServiceClient {

    private final AddressServiceGrpc.AddressServiceBlockingStub blockingStub;

    public AddressServiceClient(
            @Value("${common.service.address:localhost}") String host,
            @Value("${common.service.grpc.port:9003}")    int port
    ) {

        log.info("AddressServiceClient init...");
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        blockingStub = AddressServiceGrpc.newBlockingStub(managedChannel);
    }

    public AddressResponseDTO createAddress(AddressRequestDTO request) {

        log.info("AddressServiceClient createAddress...");

        if (request == null) {
            throw new IllegalArgumentException("Address GRPC request is null");
        }

       AddressGRPCResponse createdAddress = blockingStub.createAddress(AddressMapper.toAddressGRPCRequest(request));

        if (createdAddress == null) {
            throw new RuntimeException("Failed to create address for address");
        }

        return AddressMapper.toAddressResponseDTO(createdAddress.getAddress());

    }
}
