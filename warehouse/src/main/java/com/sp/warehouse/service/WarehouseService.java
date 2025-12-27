package com.sp.warehouse.service;

import com.sp.warehouse.dto.AddressRequestDTO;
import com.sp.warehouse.dto.AddressResponseDTO;
import com.sp.warehouse.dto.WarehouseRequestDTO;
import com.sp.warehouse.dto.WarehouseResponseDTO;
import com.sp.warehouse.grpc.AddressServiceClient;
import com.sp.warehouse.mapper.WarehouseMapper;
import com.sp.warehouse.model.Warehouse;
import com.sp.warehouse.repository.WarehouseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

}
