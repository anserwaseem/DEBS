package com.debs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.debs.model.Account;
import com.debs.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public List<Account> getAllAccounts() {
		return accountRepository.findAll();
	}

	@Override
	public int addAccount(int userId, String accountType) {
		return accountRepository.addAccount(userId, accountType);
	}

	@Override
	public List<String> getAllAccountsNames(int userId, String accountType) {
		return accountRepository.findAllAccountsNames(userId, accountType);
	}

	@Override
	public int getAccountId(String accountName, String accountType, int userId) {
		return accountRepository.getAccountId(accountName, accountType, userId);
	}
}
