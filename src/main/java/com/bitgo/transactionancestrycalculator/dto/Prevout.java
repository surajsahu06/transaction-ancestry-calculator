package com.bitgo.transactionancestrycalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Prevout {
    public String scriptpubkey;
    public String scriptpubkey_asm;
    public String scriptpubkey_type;
    public String scriptpubkey_address;
    public long value;
}