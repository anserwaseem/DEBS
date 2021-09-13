package com.debs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.debs.model.Transaction;
import com.debs.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private AccountService accountService;

	@Override
	public List<Transaction> getAllTransactions() {
		return transactionRepository.getAllTransactions();
	}

	@Override
	public Boolean commitTransaction(String rowsOfTransaction, int user_id) {
//		transactionRepository.saveAll(transaction);
		String[] split = rowsOfTransaction.split(",");
		ArrayList<Transaction> transaction = new ArrayList<Transaction>();

		for (int r = 0; r < split.length / 5; r += 5) {
			Transaction t = new Transaction();
			t.setTransaction(split[r], new java.util.Date(), split[r + 1], Integer.valueOf(split[r + 2]),
					Integer.valueOf(split[r + 3]), split[r + 4]);
			transaction.add(t);
		}

		int sumOfDebits = 0, sumOfCredits = 0;
		for (Transaction tr : transaction) {
			sumOfDebits += tr.getDebit();
			sumOfCredits += tr.getCredit();
		}
		if (sumOfDebits != sumOfCredits)
			return false;

		for (Transaction tr : transaction) {
			int output = transactionRepository.commitTransaction(user_id, tr.getAccountType(), tr.getAccount(),
					tr.getDescription(), tr.getDebit(), tr.getCredit(), "", -1, -1, -1,
					accountService.getAccountId(tr.getAccount(), tr.getAccountType(), user_id));
			System.out.println("output = " + output + "\n");
			if (output != 1 || output != 2 || output != 3)
				return false;
		}
		return true;
	}
}
