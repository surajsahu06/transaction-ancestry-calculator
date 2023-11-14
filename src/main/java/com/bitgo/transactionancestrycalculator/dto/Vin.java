package com.bitgo.transactionancestrycalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Vin {

    String txid;

    long vout;

    String prevout;

    String scriptsig;

    String scriptsig_asm;

    List<Witness> witness;

    boolean is_coinbase = false;

    long sequence;
}
