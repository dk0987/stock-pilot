package com.sp.warehouseservice.service;

import com.sp.warehouseservice.dto.WarehouseRequestDTO;
import com.sp.warehouseservice.dto.WarehouseResponseDTO;
import com.sp.warehouseservice.grpc.UserServiceClient;
import com.sp.warehouseservice.mapper.WarehouseMapper;
import com.sp.warehouseservice.model.Warehouse;
import com.sp.warehouseservice.repository.WarehouseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class WarehouseService {

    private static final Logger log = LoggerFactory.getLogger(WarehouseService.class);
    private final WarehouseRepository warehouseRepository;
    private final UserServiceClient userServiceClient;

    public WarehouseService(WarehouseRepository warehouseRepository , UserServiceClient userServiceClient) {
        this.warehouseRepository = warehouseRepository;
        this.userServiceClient = userServiceClient;
    }

    public List<WarehouseResponseDTO> getAllWarehouses(){
        try{
            return warehouseRepository.findAll().stream().map(WarehouseMapper::toWarehouseResponseDTO).toList();
        } catch (RuntimeException e) {
            log.info("Something went wrong when trying to get all warehouses.");
            throw new RuntimeException(e);
        }
    }

    public WarehouseResponseDTO getWarehouseById(UUID id){
        try{
            if (id == null){
                throw new RuntimeException("Id is null");
            }
            if(!warehouseRepository.existsById(id)){
                throw new RuntimeException("Warehouse not found");
            }
            return warehouseRepository.findById(id)
                    .map(WarehouseMapper::toWarehouseResponseDTO)
                    .orElseThrow(() -> new RuntimeException("warehouse not found"));

        } catch (RuntimeException e) {
            log.info("Something went wrong when trying to get warehouse by id {}.", id);
            throw new RuntimeException(e);
        }
    }

    public WarehouseResponseDTO createWarehouse(WarehouseRequestDTO warehouseRequest){
        try{
            if (warehouseRequest == null){
                throw new RuntimeException("warehouse create request is null");
            }
            if (!userServiceClient.checkUserExist(warehouseRequest.getManagedBy().toString())){
                throw new RuntimeException("User id for managed by not exist");
            }

            Warehouse warehouse = WarehouseMapper.toWarehouse(warehouseRequest);
            warehouse.setCreatedAt(LocalDateTime.now());
            warehouse.setCreatedBy(UUID.fromString("a0e9b8c7-d6f5-e4d3-c2b1-100000000008"));
            warehouse.setActive(true);
            Warehouse createdWarehouse = warehouseRepository.save(warehouse);
            return WarehouseMapper.toWarehouseResponseDTO(createdWarehouse);
        } catch (RuntimeException e) {
            log.info("Something went wrong when trying to create warehouse by request.");
            throw new RuntimeException(e.getMessage());
        }
    }

    public WarehouseResponseDTO updateWarehouseById(WarehouseRequestDTO warehouseRequestDTO){
        try{

            if (warehouseRequestDTO == null){
                throw new RuntimeException("Warehouse requestDTO is null");
            }

            Warehouse warehouse = warehouseRepository.findById(warehouseRequestDTO.getWarehouseId())
                    .orElseThrow(() -> new RuntimeException("Warehouse not found"));

            String warehouseName    = warehouseRequestDTO.getWarehouseName();
            String warehouseAddress = warehouseRequestDTO.getWarehouseAddress();
            String warehouseCity    = warehouseRequestDTO.getWarehouseCity();
            String warehouseState   = warehouseRequestDTO.getWarehouseState();
            String warehouseZip     = warehouseRequestDTO.getWarehouseZip();
            String warehouseCountry = warehouseRequestDTO.getWarehouseCountry();
            String warehousePhone   = warehouseRequestDTO.getWarehousePhone();
            String warehouseEmail   = warehouseRequestDTO.getWarehouseEmail();
            UUID   managedBy        = warehouseRequestDTO.getManagedBy();

            warehouse.setName(   !warehouseName.isBlank()        ? warehouseName    : warehouse.getName());
            warehouse.setAddress( warehouseAddress != null && !warehouseAddress.isBlank()     ? warehouseAddress : warehouse.getAddress());
            warehouse.setCity(   warehouseCity != null &&!warehouseCity.isBlank()        ? warehouseCity    : warehouse.getCity());
            warehouse.setState(  warehouseState != null &&!warehouseState.isBlank()       ? warehouseState   : warehouse.getState());
            warehouse.setZip(    warehouseZip != null &&!warehouseZip.isBlank()         ? warehouseZip     : warehouse.getZip());
            warehouse.setCountry(warehouseCountry != null &&!warehouseCountry.isBlank()     ? warehouseCountry : warehouse.getCountry());
            warehouse.setPhone(  warehousePhone != null &&!warehousePhone.isBlank()       ? warehousePhone   : warehouse.getPhone());
            warehouse.setEmail(  warehouseEmail != null &&!warehouseEmail.isBlank()       ? warehouseEmail   : warehouse.getEmail());
            warehouse.setUserId( managedBy != null &&!managedBy.toString().isBlank() ? managedBy        : warehouse.getUserId());

            Warehouse updatedWarehouse = warehouseRepository.save(warehouse);
            return WarehouseMapper.toWarehouseResponseDTO(updatedWarehouse);

        } catch (RuntimeException e) {
            log.info("Something went wrong when trying to update warehouse by request.");
            throw new RuntimeException(e);
        }
    }

    public boolean deleteWarehouseById(UUID warehouseId){
        try{
            if (warehouseId == null){
                throw new RuntimeException("Warehouse id is null");
            }

            Warehouse warehouse = warehouseRepository.findById(warehouseId)
                    .orElseThrow(() -> new RuntimeException("Warehouse not found"));

            warehouse.setActive(false);

            warehouseRepository.save(warehouse);
            return true;

        } catch (RuntimeException e) {
             log.info("Something went wrong when trying to delete warehouse by request.");
            throw new RuntimeException(e.getMessage());
        }

    }
}
