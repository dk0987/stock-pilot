package com.sp.warehouse.mapper;

import com.sp.warehouse.dto.AddressRequestDTO;
import com.sp.warehouse.dto.AddressResponseDTO;
import com.sp.warehouseGrpcService.Address;
import com.sp.warehouseGrpcService.AddressGRPCRequest;

public class AddressMapper {

    public static AddressGRPCRequest toAddressGRPCRequest(AddressRequestDTO request) {

        return AddressGRPCRequest.newBuilder()
                .setCity(request.getCity())
                .setCountry(request.getCountry())
                .setEmail(request.getEmail())
                .setPhone(request.getPhone())
                .setState(request.getState())
                .setStreet(request.getStreet())
                .setZip(request.getZip())
                .build();

    }

    public static AddressResponseDTO toAddressResponseDTO(Address response) {

        AddressResponseDTO address = new AddressResponseDTO();
        address.setId(response.getId());
        address.setCity(response.getCity());
        address.setCountry(response.getCountry());
        address.setEmail(response.getEmail());
        address.setPhone(response.getPhone());
        address.setState(response.getState());
        address.setStreet(response.getStreet());
        address.setZip(response.getZip());
        return address;

    }

}
