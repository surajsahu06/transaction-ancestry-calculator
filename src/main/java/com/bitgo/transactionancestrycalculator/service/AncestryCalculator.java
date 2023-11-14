package com.bitgo.transactionancestrycalculator.service;

import com.bitgo.transactionancestrycalculator.dto.Root;
import com.bitgo.transactionancestrycalculator.dto.Transaction;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AncestryCalculator {

    public List<Transaction> getLargestAncestors(Map<String, List<String>> mapOfTxToAllInputs, int count) {
        List<Transaction> allTransactions = new ArrayList<>();
        List<Transaction> res = new ArrayList<>();
        List<String> allTxKeys = new ArrayList<>(mapOfTxToAllInputs.keySet());

        for (String key : allTxKeys) {
            allTransactions.add(new Transaction(key, mapOfTxToAllInputs.get(key).size()));
        }

        allTransactions.sort((first, second) -> Integer.compare(second.getNoOfChildren(), first.getNoOfChildren()));

        return allTransactions.subList(0, count);
    }

    public Map<String, List<String>> mapToTransactionInput(List<Root> transactions) {
        Map<String, List<String>> transactionsMap = new HashMap<>();
        for (Root transaction : transactions) {
            for (int j = 0; j < transaction.vin.size(); j++) {
                transactionsMap.computeIfAbsent(transaction.txid, k -> new ArrayList<>()).add(transaction.vin.get(j).txid);
            }
        }
        return transactionsMap;
    }

    public Map<String, List<String>> findAllAncestors(Map<String, List<String>> map) {
        Map<String, List<String>> resultMap = new HashMap<>();
        List<String> transactions = new ArrayList<>(map.keySet());
        for (String transaction : transactions) {
            if (!resultMap.containsKey(transaction)) {
                resultMap.put(transaction, dfs(transaction, map, new ArrayList<>()));
            }
        }
        return resultMap;
    }

    public List<String> dfs(String transaction, Map<String, List<String>> input, List<String> res) {
        List<String> children = input.get(transaction);
        if (children == null) {
            return res;
        }
        for (String child : children) {
            if (!res.contains(child)) {
                res.add(child);
                dfs(child, input, res);
            }
        }
        return res;
    }
}