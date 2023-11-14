package com.bitgo.transactionancestrycalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Vin {
    public String txid;
    public long vout;
    public Prevout prevout;
    public String scriptsig;
    public String scriptsig_asm;
    public ArrayList<String> witness;
    public boolean is_coinbase;
    public Object sequence;
    public String inner_redeemscript_asm;
    public String inner_witnessscript_asm;
}
