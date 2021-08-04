package com.debs.service;

import java.util.List;

import com.debs.model.Customer;

public interface CustomerService {
	List<Customer> getAllCustomers();

	void saveCustomer(Customer customer);

	Customer getCustomerbyId(long id);

	void deleteCustomerById(long id);
}
