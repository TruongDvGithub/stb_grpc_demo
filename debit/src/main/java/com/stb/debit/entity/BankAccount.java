package com.stb.debit.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@ToString
public class BankAccount {

    @Id
    private String userId;
    private int money_amount;

}
