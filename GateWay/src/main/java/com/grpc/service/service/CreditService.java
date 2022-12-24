package com.grpc.service.service;

import com.stb.credit.AccountInfoRequest;
import com.stb.credit.AccountInfoResponse;
import com.stb.credit.CreditAccountServiceGrpc;
import com.stb.user.UserServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class CreditService {

    @GrpcClient("credit")
    private CreditAccountServiceGrpc.CreditAccountServiceBlockingStub accountServiceBlockingStub;

    public AccountInfoResponse getAccountInfo(String account_id){
        try{
            AccountInfoRequest accountInfoRequest = AccountInfoRequest.newBuilder()
                    .setAccountId(account_id).build();
            return  this.accountServiceBlockingStub.getAccountInfo(accountInfoRequest);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
