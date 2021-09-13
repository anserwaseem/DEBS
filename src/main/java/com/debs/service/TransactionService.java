package com.debs.service;

import java.util.List;

import com.debs.model.Transaction;

public interface TransactionService {

	List<Transaction> getAllTransactions();

	Boolean commitTransaction(String rowsOfTransaction, int user_id);
}
