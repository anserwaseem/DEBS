package com.debs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.debs.model.TrialBalance;
import com.debs.model.User;
import com.debs.repository.TrialBalanceRepository;
import com.debs.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TrialBalanceRepository tbr;

//	@Override
//	public List<User> getAllUsers(){
//		return userRepository.findAll();
//	}

	@Override
	public User getUserById(long id) {
		Optional<User> optional = userRepository.findById(id);
		User user = null;
		if (optional.isPresent()) {
			user = optional.get();
		} else {
			throw new RuntimeException(" Customer not found for id :: " + id);
		}
		return user;
	}

	@Override
	public User getUserByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if (user == null)
			throw new RuntimeException(" Customer not found for username :: " + username);
		return user;
	}

	@Override
	public int verifySignup(User user) {
		User fbu = userRepository.findByUsername(user.getUsername());

		if (fbu != null)
			if (fbu.getUsername().equals(user.getUsername()))// this username already exists, choose another one
				return -1;

		userRepository.save(user);

		return 1;// login successful
	}

	@Override
	public int verifyLogin(User user) {
		User u1 = userRepository.findByUsername(user.getUsername());
		User u2 = userRepository.findByPassword(user.getPassword());

		if (u1 == null)// if username not found in database
			return -1;
		else if (!u1.getUsername().equals(user.getUsername()))// if username incorrect
			return -2;
		else if (u2 == null)// if password not found in database
			return -3;
		else if (!u2.getPassword().equals(user.getPassword()))// if password incorrect
			return -4;

		return 1;// login successful
	}

	@Override
	public void saveUser(User user) {
		userRepository.save(user);
	}

	@Override
	public int changeUsername(User user) {
		if (user.getNewUsername().equals("") || user.getNewUsername() == null)
			return -1;

		User u = userRepository.findByUsername(user.getUsername());

		if (u == null)// if username not found in database
			return -2;
		else if (!u.getUsername().equals(user.getUsername()))// if username incorrect
			return -3;
		else if (user.getNewUsername().equals(user.getUsername()))// if new username is same as current username
			return -4;

		return 1;
	}

	@Override
	public int changePassword(User user) {
		if (user.getNewPassword().equals("") || user.getNewPassword() == null)
			return -1;

		User u = userRepository.findByPassword(user.getPassword());

		if (u == null)// if password not found in database
			return -2;
		else if (!u.getPassword().equals(user.getPassword()))// if password incorrect
			return -3;
		else if (user.getNewPassword().equals(user.getPassword()))// if new password is same as current username
			return -4;

		return 1;
	}

	@Override
	public List<TrialBalance> getTrialBalance(int id) {
		return tbr.ShowTrialBalance(id);
	}
}
