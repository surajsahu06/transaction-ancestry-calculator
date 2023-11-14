package com.bitgo.transactionancestrycalculator.web;

import com.bitgo.transactionancestrycalculator.dto.Root;
import com.bitgo.transactionancestrycalculator.dto.Transaction;
import com.bitgo.transactionancestrycalculator.service.AncestryCalculator;
import com.bitgo.transactionancestrycalculator.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class TransactionController {

    @Autowired
    AncestryCalculator ancestryCalculator;

    @Autowired
    TransactionService transactionService;

    TransactionController(AncestryCalculator ancestryCalculator) {
        this.ancestryCalculator = ancestryCalculator;
    }

    @GetMapping(value = "/print-tx-details")
    public void printTransactions() {
        String blockHash;
        List<Root> transactions = new ArrayList<>();
        try {
            blockHash = transactionService.getBlock(680000);
            transactions = transactionService.getAllTransactions(blockHash);
        } catch (Exception error) {
            throw new Error("Fatal error when trying API calls: " + error);
        }
        Map<String, List<String>> inputMap = ancestryCalculator.mapToTransactionInput(transactions);
        Map<String, List<String>> txAncestries = ancestryCalculator.findAllAncestors(inputMap);
        List<Transaction> largest = ancestryCalculator.getLargestAncestors(txAncestries, 10);
        System.out.println(largest);
    }
}
