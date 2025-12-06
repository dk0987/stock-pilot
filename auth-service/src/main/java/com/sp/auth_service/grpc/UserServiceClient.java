package com.sp.auth_service.grpc;

import com.sp.authGrpcService.UserGRPCRequest;
import com.sp.authGrpcService.UserGRPCResponse;
import com.sp.authGrpcService.UserServiceGrpc;
import com.sp.auth_service.dto.UserGRPCResponseDTO;
import com.sp.auth_service.mapper.UserMapper;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public class UserServiceClient {

    private final UserServiceGrpc.UserServiceBlockingStub userStub;

    public  UserServiceClient(
            @Value("${user.service.address:localhost}") String host,
            @Value("${user.service.grpc.port:9001}") int port
    ) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext() // remove for TLS
                .build();
        userStub = UserServiceGrpc.newBlockingStub(channel);
    }

    public UserGRPCResponseDTO getAuthenticatedUser(String userName, String email, String password) {

        // Build request
        UserGRPCRequest request = UserGRPCRequest.newBuilder()
                .setUserName(userName != null ? userName : "")
                .setEmail(email != null ? email : "")
                .setPassword(password)
                .build();

        // Call gRPC service
        UserGRPCResponse response = userStub.getUser(request);
        log.info("Fetched Authenticated User");
        return UserMapper.toUserResponseDTO(response.getUser()); // gRPC User object
    }
}
