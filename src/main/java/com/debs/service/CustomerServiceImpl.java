package com.debs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.debs.model.Customer;
import com.debs.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

//	@Autowired
//	private AccountRepository accountRepository;

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public void saveCustomer(Customer customer) {
		this.customerRepository.save(customer);
	}

	@Override
	public Customer getCustomerbyId(int id) {
		Optional<Customer> optional = customerRepository.findById(id);
		Customer customer = null;
		if (optional.isPresent()) {
			customer = optional.get();
		} else {
			throw new RuntimeException(" Customer not found for id :: " + id);
		}
		return customer;
	}

	@Override
	public void deleteCustomerById(int id) {
		this.customerRepository.deleteById(id);
	}

//	@Override
//	public List<String> getAllCustomersNames() {
//		return customerRepository.findAllCustomersNames();
//	}

////	@SuppressWarnings("null")
//	@Override
//	public int addCustomerAccount(int userId) {
////		int account_id = java.sql.Types.NUMERIC;
////		CallableStatement cstmt = null;
////		try {
////			cstmt.registerOutParameter(2, java.sql.Types.NUMERIC);
//////			cstmt.
////		} catch (SQLException e) {
////			e.printStackTrace();
////		}
//		return accountRepository.addAccount(userId, "customer");
//	}
}
