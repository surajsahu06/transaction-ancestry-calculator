package com.bitgo.transactionancestrycalculator.web;

import com.bitgo.transactionancestrycalculator.service.AncestryCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    AncestryCalculator ancestryCalculator;

    TransactionController(AncestryCalculator ancestryCalculator){
        this.ancestryCalculator = ancestryCalculator;
    }

    @GetMapping(value = "/print-tx-details")
    public void printTransactions(){
        ancestryCalculator.fetchAllTransaction();
    }
}
