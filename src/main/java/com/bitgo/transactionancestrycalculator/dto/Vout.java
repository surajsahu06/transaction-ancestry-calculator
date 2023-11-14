package com.bitgo.transactionancestrycalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Vout {

    String scriptpubkey;

    String scriptpubkey_asm;

    String scriptpubkey_type;

    String scriptpubkey_address;

    long value;
}
