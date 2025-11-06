package com.sp.warehouseservice.grpc;

import com.sp.warehouse.ExistsUserIdRequest;
import com.sp.warehouse.ExistsUserIdResponse;
import com.sp.warehouse.UserServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserServiceClient {
    private static final Logger log = LoggerFactory.getLogger(UserServiceClient.class);
    private final UserServiceGrpc.UserServiceBlockingStub blockingStub;
    public UserServiceClient(
            @Value("${user.service.address:localhost}") String serverAddress,
            @Value("${user.service.grpc.port:9002}") int serverPort
    ) {
        log.info("Initialized UserServiceClient{}{}", serverAddress, serverPort);
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress(serverAddress, serverPort).usePlaintext().build();
        blockingStub = UserServiceGrpc.newBlockingStub(managedChannel);
    }

    public boolean checkUserExist(String userId) {
        if (userId == null) {
            throw new IllegalArgumentException("UserId is null");
        }
        log.info("Checking if user exists by ID: {}", userId);
        ExistsUserIdRequest request = ExistsUserIdRequest
                .newBuilder()
                .setUserId(userId)
                .build();
        boolean response = blockingStub.getUserExists(request).getExists();
        log.info("checkUserExists: {}", response);
        return response;
    }

}
