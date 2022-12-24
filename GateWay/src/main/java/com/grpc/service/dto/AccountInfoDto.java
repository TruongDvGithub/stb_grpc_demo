package com.grpc.service.dto;

import lombok.Data;

@Data
public class AccountInfoDto {
    private String account_id;
    private Integer money_amount;
}
