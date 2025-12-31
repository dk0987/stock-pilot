package com.sp.warehouse.grpc;

import com.sp.warehouse.dto.WarehouseGRPCResponseDTO;
import com.sp.warehouse.mapper.WarehouseMapper;
import com.sp.warehouse.service.WarehouseService;
import com.sp.warehouseGrpcService.Warehouse;
import com.sp.warehouseGrpcService.WarehouseGRPCRequest;
import com.sp.warehouseGrpcService.WarehouseGRPCResponse;
import com.sp.warehouseGrpcService.WarehouseServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@GrpcService
public class WarehouseServiceServer extends WarehouseServiceGrpc.WarehouseServiceImplBase {

    private final WarehouseService warehouseService;

    public WarehouseServiceServer(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @Override
    public void fetchWarehouseByIds(
            WarehouseGRPCRequest request,
            StreamObserver<WarehouseGRPCResponse> responseObserver
    ) {
        if (request == null) {
            throw new NullPointerException("Warehouse GRPC request is null");
        }

        Set<Long> warehouseIds = new HashSet<>(request.getWarehouseIdsList());

        Set<WarehouseGRPCResponseDTO> responseDTO = warehouseService
                .getAllExistingWarehouses(warehouseIds);

        if (responseDTO.isEmpty()) {
            responseObserver.onNext(WarehouseGRPCResponse.newBuilder().build());
            responseObserver.onCompleted();
        }

        List<Warehouse> responseWarehouse = responseDTO
                .stream()
                .map(WarehouseMapper::toWarehouseGRPC)
                .toList();

        WarehouseGRPCResponse response = WarehouseGRPCResponse
                .newBuilder()
                .addAllWarehouse(responseWarehouse)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }
}
