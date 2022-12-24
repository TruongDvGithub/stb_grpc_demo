package com.grpc.service.service;

import com.stb.credit.AccountInfoRequest;
import com.stb.credit.AccountInfoResponse;
import com.stb.credit.CreditAccountServiceGrpc;
import com.stb.debit.DebitAccountInfoRequest;
import com.stb.debit.DebitAccountInfoResponse;
import com.stb.debit.DebitAccountServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class DebitService {
    @GrpcClient("debit")
    private DebitAccountServiceGrpc.DebitAccountServiceBlockingStub serviceBlockingStub;

    public DebitAccountInfoResponse getAccountInfo(String account_id){
        try{
            DebitAccountInfoRequest accountInfoRequest = DebitAccountInfoRequest.newBuilder()
                    .setAccountId(account_id).build();
            return  this.serviceBlockingStub.getAccountInfo(accountInfoRequest);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
