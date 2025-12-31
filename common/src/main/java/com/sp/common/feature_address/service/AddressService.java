package com.sp.common.feature_address.service;

import com.sp.common.feature_address.dto.AddressRequestDTO;
import com.sp.common.feature_address.dto.AddressResponseDTO;
import com.sp.common.feature_address.mapper.AddressMapper;
import com.sp.common.feature_address.modal.Address;
import com.sp.common.feature_address.repository.AddressRepository;
import com.sp.commonGrpcService.AddressGRPCRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional
    public AddressResponseDTO createAddress(AddressGRPCRequest addressRequestDTO) {

        Address alreadySavedAddress = getAddressByRequest(AddressMapper.toAddressRequestDTO(addressRequestDTO));

        if (alreadySavedAddress != null) {
            return AddressMapper.toAddressResponseDTO(alreadySavedAddress);
        }

        Address createdAddress = addressRepository.save(AddressMapper.toAddress(addressRequestDTO));

        return AddressMapper.toAddressResponseDTO(createdAddress);

    }

    @Transactional
    public AddressResponseDTO updateAddressById(Long id , AddressRequestDTO addressRequestDTO) {

        if (id == null || addressRequestDTO == null) {
            throw new IllegalArgumentException("id or addressRequestDTO is null");
        }

        Address existingAddress = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("address not found"));

        Optional.ofNullable(addressRequestDTO.getStreet()).ifPresent(existingAddress::setStreet);
        Optional.ofNullable(addressRequestDTO.getCity()).ifPresent(existingAddress::setCity);
        Optional.ofNullable(addressRequestDTO.getZip()).ifPresent(existingAddress::setZip);
        Optional.ofNullable(addressRequestDTO.getState()).ifPresent(existingAddress::setState);
        Optional.ofNullable(addressRequestDTO.getCountry()).ifPresent(existingAddress::setCountry);
        Optional.ofNullable(addressRequestDTO.getPhone()).ifPresent(existingAddress::setPhone);
        Optional.ofNullable(addressRequestDTO.getEmail()).ifPresent(existingAddress::setEmail);

        Address updatedAddress = addressRepository.save(existingAddress);
        return AddressMapper.toAddressResponseDTO(updatedAddress);

    }


    @Transactional
    public AddressResponseDTO getAddressById(Long id) {

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        return AddressMapper.toAddressResponseDTO(address);

    }

    @Transactional
    public Address getAddressByRequest(AddressRequestDTO request) {

        return addressRepository.findAddressByDetails(
                request.getStreet(),
                request.getCity(),
                request.getState(),
                request.getZip(),
                request.getCountry(),
                request.getPhone(),
                request.getEmail()
        );

    }
}
