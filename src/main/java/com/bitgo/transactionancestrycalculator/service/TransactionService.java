package com.bitgo.transactionancestrycalculator.service;

import com.bitgo.transactionancestrycalculator.dto.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class TransactionService {

    String blockHeightUrl = "https://blockstream.info/api/block-height/";

    String transactionDetailsUrl = "https://blockstream.info/api/block/%s/txs/%s";

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RestTemplate restTemplate;

    TransactionService(ObjectMapper objectMapper, RestTemplate restTemplate) {
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
    }

    public String fetchHash(int block) {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(blockHeightUrl + "/" + block, String.class);
            return response.getBody();
        } catch (Exception e) {
            System.err.println("error calling  block-height api");
        }
        return Strings.EMPTY;
    }

    public List<Transaction> fetchTransactionDetails(String transaction, String page) {
        String url = String.format(transactionDetailsUrl, transaction, page);
        try {
            ResponseEntity<ArrayList> response = restTemplate.getForEntity(url, ArrayList.class);
            return response.getBody();
        } catch (Exception e) {
            System.err.println("error calling tx api");
        }
        return new ArrayList<>();
    }

}
