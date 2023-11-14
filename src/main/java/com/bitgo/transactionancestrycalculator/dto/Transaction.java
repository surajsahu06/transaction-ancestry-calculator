package com.bitgo.transactionancestrycalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    String txid;

    int version;

    int locktime;

    List<Vin> vin;

    List<Vout> vout;

    long size;

    long weight;

    long fee;
}
