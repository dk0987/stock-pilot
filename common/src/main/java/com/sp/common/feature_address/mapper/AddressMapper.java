package com.sp.common.feature_address.mapper;

import com.sp.addressGrpcService.AddressGRPCRequest;
import com.sp.addressGrpcService.AddressGRPCResponse;
import com.sp.common.feature_address.dto.AddressRequestDTO;
import com.sp.common.feature_address.dto.AddressResponseDTO;
import com.sp.common.feature_address.modal.Address;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public static Address toAddress(AddressRequestDTO request){
        Address address = new Address();
        address.setCity(request.getCity());
        address.setCountry(request.getCountry());
        address.setStreet(request.getStreet());
        address.setState(request.getState());
        address.setZip(request.getZip());
        address.setPhone(request.getPhone());
        address.setEmail(request.getEmail());
        return address;

    }

    public static Address toAddress(AddressGRPCRequest request){
        Address address = new Address();
        address.setCity(request.getCity());
        address.setCountry(request.getCountry());
        address.setStreet(request.getStreet());
        address.setState(request.getState());
        address.setZip(request.getZip());
        address.setPhone(request.getPhone());
        address.setEmail(request.getEmail());
        return address;

    }

    public static AddressResponseDTO toAddressResponseDTO(Address address){

        AddressResponseDTO responseDTO = new AddressResponseDTO();
        responseDTO.setId(address.getId());
        responseDTO.setCity(address.getCity());
        responseDTO.setCountry(address.getCountry());
        responseDTO.setStreet(address.getStreet());
        responseDTO.setState(address.getState());
        responseDTO.setZip(address.getZip());
        responseDTO.setPhone(address.getPhone());
        responseDTO.setEmail(address.getEmail());
        return responseDTO;

    }

    public static AddressRequestDTO toAddressRequestDTO(AddressGRPCRequest request){
        AddressRequestDTO address = new AddressRequestDTO();
        address.setCity(request.getCity());
        address.setCountry(request.getCountry());
        address.setStreet(request.getStreet());
        address.setState(request.getState());
        address.setZip(request.getZip());
        address.setPhone(request.getPhone());
        address.setEmail(request.getEmail());
        return address;
    }

}
