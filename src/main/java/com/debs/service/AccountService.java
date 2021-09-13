package com.debs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.debs.model.Account;

@Service
public interface AccountService {
	List<Account> getAllAccounts();

	int addAccount(int userId, String accountType);

	List<String> getAllAccountsNames(int userId, String accountType);

	int getAccountId(String accountName, String accountType, int userId);
}
