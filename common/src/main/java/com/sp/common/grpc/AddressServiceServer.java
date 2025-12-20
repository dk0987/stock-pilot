package com.sp.common.grpc;

import com.sp.common.feature_address.dto.AddressResponseDTO;
import com.sp.common.feature_address.mapper.AddressMapper;
import com.sp.common.feature_address.service.AddressService;
import com.sp.commonGrpcService.Address;
import com.sp.commonGrpcService.AddressGRPCRequest;
import com.sp.commonGrpcService.AddressGRPCResponse;
import com.sp.commonGrpcService.AddressServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
public class AddressServiceServer extends AddressServiceGrpc.AddressServiceImplBase {

    private final AddressService addressService;

    public AddressServiceServer(AddressService addressService) {
        this.addressService = addressService;
    }

    @Override
    public void createAddress(
            AddressGRPCRequest request,
            StreamObserver<AddressGRPCResponse> responseObserver
    ) {
        log.info("AddressServiceServer createAddress request...");
        if (request == null){
            responseObserver.onError(new NullPointerException("request is null"));
            return;
        }

        AddressResponseDTO addressResponse = addressService
                .createAddress(request);

        log.info("AddressResponse: {}", addressResponse);

        Address address = Address.newBuilder()
                .setId(addressResponse.getId())
                .setStreet(addressResponse.getStreet())
                .setCity(addressResponse.getCity())
                .setState(addressResponse.getState())
                .setCountry(addressResponse.getCountry())
                .setZip(addressResponse.getZip())
                .setPhone(addressResponse.getPhone())
                .setEmail(addressResponse.getEmail())
                .build();

        AddressGRPCResponse addressGRPCResponse = AddressGRPCResponse.newBuilder()
                .setAddress(address)
                .build();

        log.info("AddressGRPCResponse: {}", addressGRPCResponse);

        responseObserver.onNext(addressGRPCResponse);
        responseObserver.onCompleted();

    }
}
