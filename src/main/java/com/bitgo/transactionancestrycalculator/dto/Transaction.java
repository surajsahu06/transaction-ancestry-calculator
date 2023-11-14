package com.bitgo.transactionancestrycalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Transaction {
    private String key;
    private int noOfChildren;
}
