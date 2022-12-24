package com.grpc.service.service;

import com.stb.user.UserResponse;
import com.stb.user.UserSearchRequest;
import com.stb.user.UserServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub userStub;

    public UserResponse getUserInfo(String loginId){
        UserSearchRequest userSearchRequest = UserSearchRequest.newBuilder()
                .setLoginId(loginId).build();

        return this.userStub.getUserGenre(userSearchRequest);
    }

}
