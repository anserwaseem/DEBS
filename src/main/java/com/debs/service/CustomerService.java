package com.debs.service;

import java.util.List;

import com.debs.model.Customer;

public interface CustomerService {
	List<Customer> getAllCustomers();

	void saveCustomer(Customer customer);

	Customer getCustomerbyId(int id);

	void deleteCustomerById(int id);

//	List<String> getAllCustomersNames();

//	int addCustomerAccount(int userId);
}
