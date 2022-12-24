package com.stb.credit.service;

import com.stb.credit.AccountInfoRequest;
import com.stb.credit.AccountInfoResponse;
import com.stb.credit.AccountInfoUpdateRequest;
import com.stb.credit.CreditAccountServiceGrpc;
import com.stb.credit.entity.BankAccount;
import com.stb.credit.repository.BankAccountRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@GrpcService
public class BankAccountService extends CreditAccountServiceGrpc.CreditAccountServiceImplBase {

    @Autowired
    private BankAccountRepository accountRepository;
    @Override
    public void getAccountInfo(AccountInfoRequest request, StreamObserver<AccountInfoResponse> responseObserver) {
        AccountInfoResponse.Builder builder = AccountInfoResponse.newBuilder();
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
    public void updateAccountInfo(AccountInfoUpdateRequest request, StreamObserver<AccountInfoResponse> responseObserver) {
        super.updateAccountInfo(request, responseObserver);
    }
}
