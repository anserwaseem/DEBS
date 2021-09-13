package com.debs.service;

import java.util.List;

import com.debs.model.TrialBalance;
import com.debs.model.User;

public interface UserService {
//	List<User> getAllUsers();
	User getUserById(int id);

	User getUserByUsername(String username);

	int verifySignup(User user);

	int verifyLogin(User user);

	void saveUser(User user);

	int changeUsername(User user);

	int changePassword(User user);

	List<TrialBalance> getTrialBalance(int id);
}
