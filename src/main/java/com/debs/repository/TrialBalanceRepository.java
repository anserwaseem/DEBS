package com.debs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.debs.model.TrialBalance;

@Repository
public interface TrialBalanceRepository extends JpaRepository<TrialBalance, Long> {
//	@Procedure
	@Query(value = "CALL ShowTrialBalance (:id);", nativeQuery = true)
	List<TrialBalance> ShowTrialBalance(@Param("id") int id);
}
