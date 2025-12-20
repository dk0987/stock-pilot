package com.sp.partners.mapper;

import com.sp.partners.dto.AddressRequestDTO;
import com.sp.partners.dto.AddressResponseDTO;

public class AddressMapper {

    public static com.sp.partnersGrpcService.AddressGRPCRequest toAddressGRPCRequest(AddressRequestDTO request) {
        return com.sp.partnersGrpcService.AddressGRPCRequest.newBuilder()
                .setCity(request.getCity())
                .setCountry(request.getCountry())
                .setState(request.getState())
                .setStreet(request.getStreet())
                .setZip(request.getZip())
                .setEmail(request.getEmail())
                .setPhone(request.getPhone())
                .build();
    }

    public static AddressResponseDTO toAddressResponseDTO(com.sp.partnersGrpcService.Address address) {
       AddressResponseDTO response = new AddressResponseDTO();
       response.setId(address.getId());
       response.setCity(address.getCity());
       response.setCountry(address.getCountry());
       response.setState(address.getState());
       response.setStreet(address.getStreet());
       response.setZip(address.getZip());
       response.setEmail(address.getEmail());
       response.setPhone(address.getPhone());
       return response;
    }
}
