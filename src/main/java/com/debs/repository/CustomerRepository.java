package com.debs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.debs.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
//	@Query(value = "select `name` from `customer`;", nativeQuery = true)
//	List<String> findAllCustomersNames();

//	@Query(value = "CALL AddCustomerAccount (:uid);", nativeQuery = true)
//	int addCustomerAccount(@Param("uid") int uid);
}

//1: add method in service and serviceImpl
//2: add button/form to make a request
//3: make a method handler in that button/form
//4: implement that method handler inside its controller
//5: redirect from that handler (inside controller) to anywhere you like