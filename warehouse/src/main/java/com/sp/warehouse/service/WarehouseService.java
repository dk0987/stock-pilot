package com.sp.warehouse.service;

import com.sp.warehouse.dto.*;
import com.sp.warehouse.grpc.AddressServiceClient;
import com.sp.warehouse.mapper.WarehouseMapper;
import com.sp.warehouse.model.Warehouse;
import com.sp.warehouse.repository.WarehouseRepository;
import com.sp.warehouseGrpcService.WarehouseGRPCRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WarehouseService {

    private final WarehouseRepository  warehouseRepository;
    private final AddressServiceClient addressServiceClient;

    public WarehouseService(
            WarehouseRepository warehouseRepository ,
            AddressServiceClient addressServiceClient
    ) {
        this.warehouseRepository = warehouseRepository;
        this.addressServiceClient = addressServiceClient;
    }

    @Transactional
    public WarehouseResponseDTO createWarehouse(
            WarehouseRequestDTO request,
            Long createdBy
    ){
        log.info("Warehouse create request received");
        if (request == null){
            throw new RuntimeException("Request can not be null");
        }

        warehouseRepository.findByWarehouseCode(request.getWarehouseCode())
                .ifPresent(warehouse -> {
                    throw new RuntimeException("Warehouse already exists");
                });


        AddressResponseDTO address = createAddress(request.getAddress());
        log.info("Warehouse address created");
        Warehouse warehouse = WarehouseMapper.toWarehouse(request , address.getId());
        warehouse.setCreatedBy(createdBy);
        warehouse.setCreatedAt(LocalDateTime.now());
        Warehouse createdWarehouse = warehouseRepository.save(warehouse);

        return WarehouseMapper.toWarehouseResponseDTO(createdWarehouse , address);

    }


    private AddressResponseDTO createAddress(AddressRequestDTO request) {

        if (request == null){
            throw new IllegalArgumentException("Address Request is null");
        }

        // Call GRPC to create address first
        AddressResponseDTO address = addressServiceClient.createAddress(request);

        if(address == null){
            throw new RuntimeException("Failed to create address");
        } else{
            return address;
        }

    }

    public Set<WarehouseGRPCResponseDTO> getAllExistingWarehouses(Set<Long> warehouseIds){

        if (warehouseIds == null || warehouseIds.isEmpty()){
            throw new IllegalArgumentException("Warehouse Ids can not be null or empty");
        }

        List<Warehouse> warehouses = warehouseRepository.findAllById(warehouseIds);

        if(warehouses.size() != warehouseIds.size()){
            throw new IllegalArgumentException("Warehouse Ids are not match");
        }

        return warehouses
                .stream()
                .map(WarehouseMapper::toWarehouseGRPCResponseDTO)
                .collect(Collectors.toSet());

    }

}
