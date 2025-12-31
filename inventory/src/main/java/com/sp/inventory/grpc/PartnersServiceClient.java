package com.sp.inventory.grpc;

import com.example.grpcProto.PartnerServiceGrpc;
import com.example.grpcProto.PartnersGRPCRequest;
import com.example.grpcProto.PartnersGRPCResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PartnersServiceClient {

    private final PartnerServiceGrpc.PartnerServiceBlockingStub blockingStub;

    public PartnersServiceClient(
            @Value("${partners.service.address:localhost}") String host,
            @Value("${partners.service.grpc.port:9004}")    int port
    ) {

       log.info("Warehouse GRPC service initiated...");
       ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
               .usePlaintext()
               .build();
       blockingStub = PartnerServiceGrpc.newBlockingStub(channel);

    }

    public boolean doesPartnerExist(Long partnerId){

        PartnersGRPCRequest request = PartnersGRPCRequest.newBuilder()
                .setPartnerId(partnerId)
                .build();

        PartnersGRPCResponse response = blockingStub.fetchPartnerExist(request);

        if (response == null) {
            throw new RuntimeException(String.format("Could not fetch Partner with id %d ", partnerId));
        }

        return response.getDoesExist();

    }

}
