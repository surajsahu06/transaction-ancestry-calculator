package com.bitgo.transactionancestrycalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Status {
    public boolean confirmed;
    public int block_height;
    public String block_hash;
    public int block_time;
}
