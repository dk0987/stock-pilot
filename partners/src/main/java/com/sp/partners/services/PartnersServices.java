package com.sp.partners.services;

import com.sp.addressGrpcService.Address;
import com.sp.addressGrpcService.AddressGRPCResponse;
import com.sp.addressGrpcService.AddressServiceGrpc;
import com.sp.partners.dto.AddressRequestDTO;
import com.sp.partners.dto.AddressResponseDTO;
import com.sp.partners.dto.PartnersRequestDTO;
import com.sp.partners.dto.PartnersResponseDTO;
import com.sp.partners.grpc.AddressServiceClient;
import com.sp.partners.mapper.AddressMapper;
import com.sp.partners.mapper.PartnersMapper;
import com.sp.partners.model.Partners;
import com.sp.partners.repository.PartnersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PartnersServices {

    @Autowired
    private PartnersRepository partnersRepository;

    @Autowired
    private AddressServiceClient addressServiceClient;

    public PartnersResponseDTO createPartner(
            PartnersRequestDTO request ,
            Long partnerType,
            Long createdBy
    ) {

        if (request == null){
            throw new IllegalArgumentException("request is null");
        }

        AddressResponseDTO createdAddress = createAddress(request.getAddress());

        Partners newPartner = PartnersMapper.toPartners(request);

        newPartner.setPartnerTypeId(partnerType);
        newPartner.setAddressId(createdAddress.getId());
        newPartner.setCreatedBy(createdBy);
        newPartner.setCreatedAt(LocalDateTime.now());

        Partners createdPartner = partnersRepository.save(newPartner);
        return PartnersMapper.toPartnersResponseDTO(createdPartner , createdAddress);

    }

    private AddressResponseDTO createAddress(AddressRequestDTO request) {

        if (request == null){
            throw new IllegalArgumentException("request is null");
        }

        // Call GRPC to create address first
        AddressGRPCResponse addressGRPCResponse = addressServiceClient
                .createAddress(AddressMapper.toAddressGRPCRequest(request));


        return AddressMapper.toAddressResponseDTO(addressGRPCResponse.getAddress());

    }
}
