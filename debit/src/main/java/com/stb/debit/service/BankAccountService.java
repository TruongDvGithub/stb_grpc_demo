package com.stb.debit.service;

import com.stb.debit.DebitAccountInfoRequest;
import com.stb.debit.DebitAccountInfoResponse;
import com.stb.debit.DebitAccountInfoUpdateRequest;
import com.stb.debit.DebitAccountServiceGrpc;
import com.stb.debit.entity.BankAccount;
import com.stb.debit.repository.BankAccountRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@GrpcService
public class BankAccountService extends DebitAccountServiceGrpc.DebitAccountServiceImplBase {

    @Autowired
    private BankAccountRepository accountRepository;

    @Override
    public void getAccountInfo(DebitAccountInfoRequest request, StreamObserver<DebitAccountInfoResponse> responseObserver) {
        DebitAccountInfoResponse.Builder builder = DebitAccountInfoResponse.newBuilder();
        Optional<BankAccount> bankAccountInfo = this.accountRepository.findById(request.getAccountId());

        if(bankAccountInfo.isPresent()){
            var accountInfo = bankAccountInfo.get();
            builder.setLoginId(accountInfo.getUserId())
                    .setMoneyAmount(accountInfo.getMoney_amount())
                    .build();
        }
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void updateAccountInfo(DebitAccountInfoUpdateRequest request, StreamObserver<DebitAccountInfoResponse> responseObserver) {
        super.updateAccountInfo(request, responseObserver);
    }
}
