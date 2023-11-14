package com.bitgo.transactionancestrycalculator.service;

import com.bitgo.transactionancestrycalculator.dto.Transaction;
import com.bitgo.transactionancestrycalculator.dto.Vin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class AncestryCalculator {

    @Autowired
    TransactionService transactionService;

    public void fetchAllTransaction(){
        String h  = transactionService.fetchHash(68000);
        String page = "0";
        List<Transaction> list = transactionService.fetchTransactionDetails(h, page);
        System.out.println(list);
    }
}
