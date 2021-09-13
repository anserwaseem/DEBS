package com.debs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.debs.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	@Query(value = "select * from `transaction`", nativeQuery = true)
	List<Transaction> getAllTransactions();

	@Query(value = "CALL AddTransaction (:uid, :atype, :atype_name, :dsc, :dr, :cr, :productName, :product_id, :quantity, :price, :aid);", nativeQuery = true)
	int commitTransaction(@Param("uid") Integer uid, @Param("atype") String atype,
			@Param("atype_name") String atype_name, @Param("dsc") String dsc, @Param("dr") Integer dr,
			@Param("cr") Integer cr, @Param("productName") String productName, @Param("product_id") Integer product_id,
			@Param("quantity") Integer quantity, @Param("price") Integer price, @Param("aid") Integer aid);
}
