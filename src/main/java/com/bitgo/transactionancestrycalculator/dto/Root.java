package com.bitgo.transactionancestrycalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Root {
    public String txid;
    public int version;
    public long locktime;
    public ArrayList<Vin> vin;
    public ArrayList<Vout> vout;
    public int size;
    public int weight;
    public int fee;
    public Status status;
}

