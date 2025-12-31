package com.sp.partners.grpc;

import com.sp.partners.services.PartnersServices;
import com.sp.partnersGrpcService.PartnerServiceGrpc;
import com.sp.partnersGrpcService.PartnersGRPCRequest;
import com.sp.partnersGrpcService.PartnersGRPCResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class PartnersServiceServer extends PartnerServiceGrpc.PartnerServiceImplBase {

    private final PartnersServices partnersServices;

    public PartnersServiceServer(PartnersServices partnersServices) {
        this.partnersServices = partnersServices;
    }

    @Override
    public void fetchPartnerExist(
            PartnersGRPCRequest request,
            StreamObserver<PartnersGRPCResponse> responseObserver
    ) {
        boolean partnerExist = partnersServices.doesPartnerExist(request.getPartnerId());

        PartnersGRPCResponse response = PartnersGRPCResponse
                .newBuilder()
                .setDoesExist(partnerExist)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }
}
