package com.grpcflix.user.service;

import com.grpcflix.user.repository.UserRepository;
import com.stb.common.Genre;
import com.stb.user.UserGenreUpdateRequest;
import com.stb.user.UserResponse;
import com.stb.user.UserSearchRequest;
import com.stb.user.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

@GrpcService
public class UserService extends UserServiceGrpc.UserServiceImplBase {

    @Autowired
    private UserRepository repository;
    @Override
    public void getUserGenre(UserSearchRequest request, StreamObserver<UserResponse> responseObserver) {
        UserResponse.Builder userBuilder = UserResponse.newBuilder();

        var user = this.repository.findById(request.getLoginId());
        if(user.isPresent()){
            var temp = user.get();
            userBuilder.setLoginId(temp.getLogin())
                    .setName(temp.getName())
                    .setGenre(Genre.valueOf(temp.getGenre().toUpperCase()))
                    .build();
        }
        responseObserver.onNext(userBuilder.build());
        responseObserver.onCompleted();
    }

    @Override
    @Transactional
    public void updateUserGenre(UserGenreUpdateRequest request, StreamObserver<UserResponse> responseObserver) {

        UserResponse.Builder userBuilder = UserResponse.newBuilder();
        this.repository.findById(request.getLoginId())
                .ifPresent(user -> {
                    user.setGenre(request.getGenre().toString());
                    userBuilder.setName(user.getName())
                            .setLoginId(user.getLogin())
                            .setGenre(Genre.valueOf(user.getGenre().toUpperCase()));
                });
        responseObserver.onNext(userBuilder.build());
        responseObserver.onCompleted();
    }
}
