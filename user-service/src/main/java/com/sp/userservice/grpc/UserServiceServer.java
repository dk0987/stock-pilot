// package com.sp.userservice.grpc;

// import com.sp.userservice.service.UsersService;
// import com.sp.warehouse.ExistsUserIdRequest;
// import com.sp.warehouse.ExistsUserIdResponse;
// import com.sp.warehouse.UserServiceGrpc;
// import io.grpc.stub.StreamObserver;
// import net.devh.boot.grpc.server.service.GrpcService;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// import java.util.UUID;

// @GrpcService
// public class UserServiceServer extends UserServiceGrpc.UserServiceImplBase {

//     private static final Logger log = LoggerFactory.getLogger(UserServiceServer.class);
//     private final UsersService userService;

//     public UserServiceServer(UsersService userService) {
//         this.userService = userService;
//     }

//     @Override
//     public void getUserExists(
//             ExistsUserIdRequest request,
//             StreamObserver<ExistsUserIdResponse> responseObserver
//     ) {
//         log.info("getUserExists: {}", request);
//         boolean doesUserExists = userService.getUserExistsById(UUID.fromString(request.getUserId()));
//         log.info("getUserExists: {}", doesUserExists);
//         responseObserver.onNext(ExistsUserIdResponse.newBuilder().setExists(doesUserExists).build());
//         responseObserver.onCompleted();
//     }
// }
