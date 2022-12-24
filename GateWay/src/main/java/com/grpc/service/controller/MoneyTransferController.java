package com.grpc.service.controller;

import com.grpc.service.dto.AccountInfoDto;
import com.grpc.service.service.CreditService;
import com.grpc.service.service.DebitService;
import com.stb.credit.AccountInfoResponse;
import com.stb.debit.DebitAccountInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class MoneyTransferController {

    @Autowired
    private CreditService creditService;

    @Autowired
    private DebitService debitService;

    @GetMapping("getCreditAccountInfo/{account_id}")
    public ResponseEntity<AccountInfoDto> getAccountInfo(@PathVariable String account_id){
        AccountInfoResponse accountInfo = this.creditService.getAccountInfo(account_id);
        return ResponseEntity.ok(mapEntityToDtoCredit(accountInfo));
    }
    @GetMapping("getDebitAccountInfo/{account_id}")
    public ResponseEntity<AccountInfoDto> getDebitAccountInfo(@PathVariable String account_id){
        DebitAccountInfoResponse accountInfo = this.debitService.getAccountInfo(account_id);
        return ResponseEntity.ok(mapEntityToDtoDebit(accountInfo));
    }

    private AccountInfoDto mapEntityToDtoCredit(AccountInfoResponse accountInfo){
        AccountInfoDto accountInfoDto = new AccountInfoDto();
        accountInfoDto.setAccount_id(accountInfo.getLoginId());
        accountInfoDto.setMoney_amount(accountInfo.getMoneyAmount());
        return accountInfoDto;
    }
    private AccountInfoDto mapEntityToDtoDebit(DebitAccountInfoResponse accountInfo){
        AccountInfoDto accountInfoDto = new AccountInfoDto();
        accountInfoDto.setAccount_id(accountInfo.getLoginId());
        accountInfoDto.setMoney_amount(accountInfo.getMoneyAmount());
        return accountInfoDto;
    }
}
