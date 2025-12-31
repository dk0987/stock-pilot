package com.sp.inventory.grpc;

import com.example.grpcProto.WarehouseGRPCRequest;
import com.example.grpcProto.WarehouseGRPCResponse;
import com.example.grpcProto.WarehouseServiceGrpc;
import com.sp.inventory.dto.WarehouseGRPCResponseDTO;
import com.sp.inventory.mapper.WarehouseMapper;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WarehouseServiceClient {

    private final WarehouseServiceGrpc.WarehouseServiceBlockingStub warehouseServiceBlockingStub;

    public WarehouseServiceClient(
            @Value("${warehouse.service.address:localhost}") String host,
            @Value("${warehouse.service.grpc.port:9005}")    int port
    ){

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();

        warehouseServiceBlockingStub = WarehouseServiceGrpc.newBlockingStub(channel);

    }

    public Set<WarehouseGRPCResponseDTO> getExistingWarehouses(Set<Long> warehouseIDs) {

        WarehouseGRPCRequest request = WarehouseGRPCRequest
                .newBuilder()
                .addAllWarehouseIds(warehouseIDs)
                .build();

        WarehouseGRPCResponse response = warehouseServiceBlockingStub.fetchWarehouseByIds(request);

        return response.getWarehouseList()
                .stream()
                .map(WarehouseMapper::toWarehouseGRPCResponseDTO)
                .collect(Collectors.toSet());

    }

}
