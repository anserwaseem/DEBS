package com.debs.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
		System.out.println("\ninside1\n");
		ArrayList<String> split = new ArrayList<String>(Arrays.asList(rowsOfTransaction.split(",")));

		System.out.println("\nsplit.length: " + split.size());
		if (split.size() % 5 != 0)
			split.add("");
		System.out.println("\nsplit.length: " + split.size());
		for (int i = 0; i < split.size(); i++)
			System.out.println(split.get(i));

		ArrayList<Transaction> transaction = new ArrayList<Transaction>();

		for (int r = 0; r < split.size(); r += 5) {
			Transaction t = new Transaction();
//			new Timestamp(System.currentTimeMillis())
			// date is automatically set (as current datetime (date.now()) in db)
			t.setTransaction(split.get(r).substring(0, split.get(r).length() - 1), new Date(System.currentTimeMillis()),
					split.get(r + 1), Integer.valueOf(split.get(r + 2)), Integer.valueOf(split.get(r + 3)),
					split.get(r + 4));
			transaction.add(t);
		}
		System.out.println("\ninside2\n");
		int sumOfDebits = 0, sumOfCredits = 0;
		for (int i = 0; i < transaction.size(); i++) {
			System.out.println(transaction.get(i).getDebit() + "<--->" + transaction.get(i).getCredit());
			sumOfDebits += transaction.get(i).getDebit();
			sumOfCredits += transaction.get(i).getCredit();
		}
		System.out.println(sumOfDebits + "<->" + sumOfCredits);
		if (sumOfDebits != sumOfCredits)
			return false;
		System.out.println("\ninside3\n");
//		for (int i = 0; i < transaction.size(); i++)
//			System.out.println(transaction.get(i).toString());

		ArrayList<Integer> outputArray = new ArrayList<Integer>();
		for (int i = 0; i < transaction.size(); i++) {
			Transaction tr = new Transaction();
			tr.setTransaction(transaction.get(i));
			System.out.println(tr.toString());

			int accountId = accountService.getAccountId(tr.getAccount(), tr.getAccountType(), user_id);
			System.out.println("accountId" + accountId);

			int output = transactionRepository.commitTransaction(user_id, tr.getAccountType(), tr.getAccount(),
					tr.getDescription(), tr.getDebit(), tr.getCredit(), "", -1, -1, -1, accountId);
			System.out.println("output = " + output + "\n");
			outputArray.add(output);
		}

		for (int output : outputArray) {
			// output must be 1 (simple transaction) or 2 (transaction of purchase of
			// product from vendor) or 3 (transaction of sale of product to customer)
			if (output < 1 || output > 3)
				return false;
		}

		return true;
	}
}
