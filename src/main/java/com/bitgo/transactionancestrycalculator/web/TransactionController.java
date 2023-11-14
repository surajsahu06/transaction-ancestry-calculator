package com.bitgo.transactionancestrycalculator.web;

import com.bitgo.transactionancestrycalculator.dto.Root;
import com.bitgo.transactionancestrycalculator.dto.Transaction;
import com.bitgo.transactionancestrycalculator.service.AncestryCalculator;
import com.bitgo.transactionancestrycalculator.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class TransactionController {

    @Autowired
    AncestryCalculator ancestryCalculator;

    @Autowired
    TransactionService transactionService;

    TransactionController(AncestryCalculator ancestryCalculator, TransactionService transactionService) {
        this.ancestryCalculator = ancestryCalculator;
        this.transactionService = transactionService;
    }

    @GetMapping(value = "/print-tx-details")
    public ResponseEntity<List<Transaction>> printTransactions() {
        String blockHash;
        List<Root> transactions = new ArrayList<>();
        try {
            blockHash = transactionService.getBlock(680000);
            transactions = transactionService.getAllTransactions(blockHash);
        } catch (Exception e) {
            throw new Error("Error calling API's" + e);
        }
        Map<String, List<String>> ancestorsMap = ancestryCalculator.mapToTransactionInput(transactions);
        List<Transaction> res = ancestryCalculator.getLargestAncestors(ancestorsMap, 10);
        res.forEach(System.out::println);
        return ResponseEntity.ok(res);
    }
}
