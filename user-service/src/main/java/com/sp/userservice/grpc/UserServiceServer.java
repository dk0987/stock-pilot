 package com.sp.userservice.grpc;

 import com.sp.usersGrpcService.User;
 import com.sp.usersGrpcService.UserGRPCRequest;
 import com.sp.usersGrpcService.UserGRPCResponse;
 import com.sp.usersGrpcService.UserServiceGrpc;
 import com.sp.userservice.model.Authority;
 import com.sp.userservice.model.Users;
 import com.sp.userservice.service.UsersService;
 import io.grpc.stub.StreamObserver;
 import net.devh.boot.grpc.server.service.GrpcService;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;

 import java.util.Set;
 import java.util.stream.Collectors;

 @GrpcService
 public class UserServiceServer extends UserServiceGrpc.UserServiceImplBase {

     private static final Logger log = LoggerFactory.getLogger(UserServiceServer.class);
     private final UsersService userService;

     public UserServiceServer(UsersService userService) {
         this.userService = userService;
     }

     @Override
     public void getUserId(
            UserGRPCRequest request,
            StreamObserver<UserGRPCResponse> responseObserver
     ) {
         Users dbUser = userService
                 .authenticateUser(
                         request.getUserName(),
                         request.getEmail(),
                         request.getPassword()
                 );

         if (dbUser == null) {
             throw new RuntimeException("User not found or not authenticated");
         }

         // Convert authorities to a Set<Long> or List<Long>
         Set<Long> allowedAuthorities = dbUser.getAuthorities().stream()
                 .map(Authority::getId)
                 .collect(Collectors.toSet());

         User grpcUser = User.newBuilder()
                         .setId(dbUser.getId())
                         .addAllAuthority(allowedAuthorities)
                         .build();

         log.info("User Authenticated : {}", dbUser.getId());
         responseObserver.onNext(UserGRPCResponse.newBuilder().setUser(grpcUser).build());
         responseObserver.onCompleted();
     }

 }
