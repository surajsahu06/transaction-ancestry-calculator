package com.bitgo.transactionancestrycalculator.service;

import com.bitgo.transactionancestrycalculator.dto.Root;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class TransactionService {

    String blockHeightUrl = "https://blockstream.info/api/block-height/";

    String transactionDetailsUrl = "https://blockstream.info/api/block/%s/txs/";

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RestTemplate restTemplate;

    TransactionService(ObjectMapper objectMapper, RestTemplate restTemplate) {
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
    }

    public String getBlock(int blockNumber) throws Exception {
        try {
            return restTemplate.getForObject(blockHeightUrl + "/" + blockNumber, String.class);
        } catch (Exception error) {
            throw new Exception("Error calling block-height api");
        }
    }

    public List<Root> getAllTransactions(String hash) throws Exception {
        String url = String.format(transactionDetailsUrl, hash);
        int index = 0;
        List<Root> list = new ArrayList<>();
        while (true) {
            try {
                String res = restTemplate.getForObject(url + "/" + index, String.class);
                Root[] roots = objectMapper.readValue(res, Root[].class);
                list.addAll(Arrays.asList(roots));
                index = index + 25;
            } catch (Exception e) {
                System.err.println(e.getMessage());
                break;
            }
        }
        return list;
    }
}

