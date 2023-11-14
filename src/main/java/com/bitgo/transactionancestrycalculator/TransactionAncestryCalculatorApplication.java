package com.bitgo.transactionancestrycalculator;

import com.bitgo.transactionancestrycalculator.dto.Transaction;
import com.bitgo.transactionancestrycalculator.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TransactionAncestryCalculatorApplication {

	@Autowired
	TransactionService transactionService;

	public static void main(String[] args) {
		SpringApplication.run(TransactionAncestryCalculatorApplication.class, args);

	}
}
