package com.sp.partners.services;

import com.sp.partners.dto.AddressRequestDTO;
import com.sp.partners.dto.AddressResponseDTO;
import com.sp.partners.dto.PartnersRequestDTO;
import com.sp.partners.dto.PartnersResponseDTO;
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

    public PartnersResponseDTO createPartner(
            PartnersRequestDTO request ,
            Long partnerType,
            Long createdBy
    ) {

        if (request == null){
            throw new IllegalArgumentException("request is null");
        }

        AddressResponseDTO createdAddress = createAddress(request.getAddress());

        if (createdAddress == null){
            throw new IllegalArgumentException("Error occurred while creating address");
        }

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
        return null;

    }
}
