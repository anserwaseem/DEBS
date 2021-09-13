package com.debs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.debs.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);// for changeUsername functionality

	User findByPassword(String password);// for changePassword functionality

	User findByEmail(String email);// for forgetPassword functionality (not implemented yet)

//	@Query("select name from customer \n"
//			+ "union\n"
//			+ "select name from vendor\n"
//			+ "union\n"
//			+ "select name from asset\n"
//			+ "union\n"
//			+ "select name from liability\n"
//			+ "union\n"
//			+ "select name from expense\n"
//			+ "union\n"
//			+ "select name from partner\n"
//			+ "union\n"
//			+ "select name from product")
//	List<String> findAllAccounts();
}