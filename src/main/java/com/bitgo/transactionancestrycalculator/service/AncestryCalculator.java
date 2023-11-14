package com.bitgo.transactionancestrycalculator.service;

import com.bitgo.transactionancestrycalculator.dto.Root;
import com.bitgo.transactionancestrycalculator.dto.Transaction;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AncestryCalculator {

    public List<Transaction> getLargestAncestors(Map<String, List<String>> inputMap, int count) {
        List<Transaction> transactions = new ArrayList<>();
        for (String key : inputMap.keySet()) {
            transactions.add(new Transaction(key, inputMap.get(key).size()));
        }
        transactions.sort((first, second) -> Integer.compare(second.getNoOfChildren(), first.getNoOfChildren()));
        return transactions.subList(0, count);
    }

    public Map<String, List<String>> mapToTransactionInput(List<Root> transactions) {
        Map<String, List<String>> transactionsMap = new HashMap<>();
        for (Root transaction : transactions) {
            for (int j = 0; j < transaction.vin.size(); j++) {
                transactionsMap.computeIfAbsent(transaction.txid, k -> new ArrayList<>()).add(transaction.vin.get(j).txid);
            }
        }
        Map<String, List<String>> resultMap = new HashMap<>();
        for (String transaction : transactionsMap.keySet()) {
            if (!resultMap.containsKey(transaction)) {
                List<String> tx = new ArrayList<>();
                dfs(transaction, transactionsMap, tx);
                resultMap.put(transaction, tx);
            }
        }
        return resultMap;
    }

    public void dfs(String transaction, Map<String, List<String>> input, List<String> res) {
        List<String> children = input.get(transaction);
        if(children == null) return;
        for (String child : children) {
            if (!res.contains(child)) {
                res.add(child);
                dfs(child, input, res);
            }
        }
    }
}